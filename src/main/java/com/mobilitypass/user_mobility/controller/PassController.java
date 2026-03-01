package com.mobilitypass.user_mobility.controller;

import com.mobilitypass.user_mobility.beans.MobilityPass;
import com.mobilitypass.user_mobility.service.PassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(passService.getUserPass(userId));
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
     * Met à jour le statut du pass de l'utilisateur courant.
     */
    @PatchMapping("/me/status")
    public ResponseEntity<Void> updateMyStatus(
            @RequestHeader("X-User-Id") String userId,
            @RequestParam String status) {
        passService.changePassStatus(userId, status);
        return ResponseEntity.noContent().build();
    }

    /**
     * Ajoute un montant consommé au pass de l'utilisateur courant.
     */
    @PatchMapping("/me/spent")
    public ResponseEntity<Void> addMySpent(
            @RequestHeader("X-User-Id") String userId,
            @RequestParam Double amount) {
        passService.addSpentAmount(userId, amount);
        return ResponseEntity.noContent().build();
    }

    // =========================================================================
    // Endpoints inter-services — accessibles par keycloakId (usage interne)
    // Appelés par d'autres microservices via Feign (trip-management, billing…)
    // =========================================================================

    /**
     * Retourne le pass d'un utilisateur par son Keycloak ID.
     * Usage inter-services uniquement.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<MobilityPass> getPass(@PathVariable String userId) {
        return ResponseEntity.ok(passService.getUserPass(userId));
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
            @RequestParam String status) {
        passService.changePassStatus(userId, status);
        return ResponseEntity.noContent().build();
    }

    /**
     * Ajoute un montant consommé au pass par Keycloak ID.
     * Usage inter-services (appelé par trip-management après validation d'un
     * trajet).
     */
    @PatchMapping("/{userId}/spent")
    public ResponseEntity<Void> updateSpent(
            @PathVariable String userId,
            @RequestParam Double amount) {
        passService.addSpentAmount(userId, amount);
        return ResponseEntity.noContent().build();
    }
}
