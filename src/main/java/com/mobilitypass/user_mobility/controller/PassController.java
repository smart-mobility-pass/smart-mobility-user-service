package com.mobilitypass.user_mobility.controller;

import com.mobilitypass.user_mobility.beans.MobilityPass;
import com.mobilitypass.user_mobility.enums.PassStatus;
import com.mobilitypass.user_mobility.service.PassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mobilitypass.user_mobility.proxy.BillingProxy;
import com.mobilitypass.user_mobility.repository.PassOfferRepository;
import com.mobilitypass.user_mobility.beans.PassOffer;
import com.mobilitypass.user_mobility.repository.MobilityPassRepository;

/**
 * Contrôleur pour la gestion des passes de mobilité.
 *
 * <p>
 * <strong>Architecture Gateway :</strong><br>
 * L'identité de l'utilisateur est fournie via le header {@code X-User-Id}
 * injecté par le Gateway. Les endpoints "mon pass" ({@code /me/*}) lisent
 * cet header pour identifier l'utilisateur courant.
 *
 * <p>
 * Les endpoints {@code /{userId}/*} sont réservés aux appels inter-services
 * (ex: trip-management qui vérifie le pass avant de valider un trajet).
 */
@Slf4j
@RestController
@RequestMapping("/api/passes")
@RequiredArgsConstructor
public class PassController {

    private final PassService passService;
    private final BillingProxy billingProxy;
    private final PassOfferRepository passOfferRepository;
    private final MobilityPassRepository passRepository;

    // =========================================================================
    // Endpoints "mon pass" — lus depuis le header X-User-Id (Gateway)
    // =========================================================================

    /**
     * Retourne le pass de l'utilisateur actuellement authentifié.
     */
    @GetMapping("/me")
    public ResponseEntity<MobilityPass> getMyPass(
            @RequestHeader("X-User-Id") String userId) {
        log.debug("Récupération du pass → userId: {}", userId);
        return passRepository.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    /**
     * Active le pass de l'utilisateur actuellement authentifié.
     */
    @PostMapping("/me/activate")
    public ResponseEntity<MobilityPass> activateMyPass(
            @RequestHeader("X-User-Id") String userId) {
        log.info("Activation du pass → userId: {}", userId);
        return ResponseEntity.ok(passService.activatePass(userId));
    }

    /**
     * Achète un pass du catalogue pour l'utilisateur courant :
     * 1) débite le compte via BillingProxy
     * 2) crée/active le pass basé sur l'offre
     */
    @PostMapping("/me/buy/{offerId}")
    public ResponseEntity<MobilityPass> buyPass(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable Long offerId) {
        log.info("Achat de pass → userId: {}, offerId: {}", userId, offerId);

        PassOffer offer = passOfferRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Pass offer not found: " + offerId));

        // Appel au service de facturation (synchronous charge)
        try {
            billingProxy.charge(userId, new com.mobilitypass.user_mobility.proxy.BillingProxy.ChargeRequest(offer.getPrice(), "Purchase pass: " + offer.getName()));
        } catch (Exception ex) {
            log.warn("Billing charge failed for userId={}, offerId={}: {}", userId, offerId, ex.getMessage());
            return ResponseEntity.status(402).build(); // Payment Required
        }

        MobilityPass pass = passService.activatePassFromOffer(userId, offerId);
        return ResponseEntity.ok(pass);
    }

    /**
     * Met à jour le statut du pass de l'utilisateur courant.
     */
    @PatchMapping("/me/status")
    public ResponseEntity<Void> updateMyStatus(
            @RequestHeader("X-User-Id") String userId,
            @RequestParam PassStatus status) {
        passService.changePassStatus(userId, status);
        return ResponseEntity.noContent().build();
    }

    // =========================================================================
    // Endpoints inter-services — accessibles par keycloakId (usage interne)
    // =========================================================================

    /**
     * Retourne le pass d'un utilisateur par son Keycloak ID.
     * Usage inter-services uniquement.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<MobilityPass> getPass(@PathVariable String userId) {
        return passRepository.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    /**
     * Active le pass d'un utilisateur par son Keycloak ID.
     * Usage inter-services (appelé par UserService lors de la création de profil).
     */
    @PostMapping("/activate/{userId}")
    public ResponseEntity<MobilityPass> activate(@PathVariable String userId) {
        return ResponseEntity.ok(passService.activatePass(userId));
    }

    /**
     * Met à jour le statut du pass par Keycloak ID.
     * Usage inter-services (ex: billing suspend le pass si solde insuffisant).
     */
    @PatchMapping("/{userId}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable String userId,
            @RequestParam PassStatus status) {
        passService.changePassStatus(userId, status);
        return ResponseEntity.noContent().build();
    }
}
