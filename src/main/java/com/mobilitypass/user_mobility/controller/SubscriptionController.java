package com.mobilitypass.user_mobility.controller;

import com.mobilitypass.user_mobility.beans.Subscriptions;
import com.mobilitypass.user_mobility.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour la gestion des abonnements.
 * Gère la création et la récupération des abonnements actifs d'un utilisateur.
 */
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<Subscriptions> create(@RequestBody Subscriptions sub) {
        Subscriptions savedSub = subscriptionService.createSubscription(
                sub.getUserId(),
                sub.getSubscriptionType(),
                sub.getDiscountPercentage());
        return ResponseEntity.ok(savedSub);
    }

    @GetMapping("/active/{userId}")
    public ResponseEntity<List<Subscriptions>> getActiveSubscriptions(@PathVariable String userId) {
        return ResponseEntity.ok(subscriptionService.getActiveSubscriptions(userId));
    }
}
