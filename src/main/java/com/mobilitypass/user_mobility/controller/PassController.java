package com.mobilitypass.user_mobility.controller;

import com.mobilitypass.user_mobility.beans.MobilityPass;
import com.mobilitypass.user_mobility.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur pour la gestion des passes de mobilité.
 * Permet l'activation, la consultation et la mise à jour (statut et
 * consommation).
 */
@RestController
@RequestMapping("/api/passes")
public class PassController {
    @Autowired
    private PassService passService;

    @PostMapping("/activate/{userId}")
    public ResponseEntity<MobilityPass> activate(@PathVariable String userId) {
        return ResponseEntity.ok(passService.activatePass(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MobilityPass> getPass(@PathVariable String userId) {
        return ResponseEntity.ok(passService.getUserPass(userId));
    }

    @PatchMapping("/{userId}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable String userId, @RequestParam String status) {
        passService.changePassStatus(userId, status);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/spent")
    public ResponseEntity<Void> updateSpent(@PathVariable String userId, @RequestParam Double amount) {
        passService.addSpentAmount(userId, amount);
        return ResponseEntity.noContent().build();
    }
}
