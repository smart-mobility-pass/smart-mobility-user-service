package com.mobilitypass.user_mobility.controller;

import com.mobilitypass.user_mobility.beans.UserProfile;
import com.mobilitypass.user_mobility.dto.UserMobilitySummaryDTO;
import com.mobilitypass.user_mobility.dto.UserProfileDTO;
import com.mobilitypass.user_mobility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur pour la gestion des profils utilisateurs.
 * Ce contrôleur permet de créer un profil métier lié à un ID Keycloak
 * et de récupérer les informations d'un utilisateur.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserProfile> getMe(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(userService.getOrCreateProfile(jwt));
    }

    @GetMapping("/summary/{keycloakId}")
    public ResponseEntity<UserMobilitySummaryDTO> getSummary(@PathVariable String keycloakId) {
        return ResponseEntity.ok(userService.getSummary(keycloakId));
    }

    @PostMapping("/profile")
    public ResponseEntity<UserProfile> createProfile(@RequestBody UserProfileDTO dto) {
        return ResponseEntity.ok(userService.createProfile(dto));
    }

    @GetMapping("/{keycloakId}")
    public ResponseEntity<UserProfile> getUser(@PathVariable String keycloakId) {
        return ResponseEntity.ok(userService.getUser(keycloakId));
    }
}
