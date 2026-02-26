package com.mobilitypass.user_mobility.controller;


import com.mobilitypass.user_mobility.beans.Subscriptions;
import com.mobilitypass.user_mobility.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Flow;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<Subscriptions> create(@RequestBody Subscriptions sub) {
        Subscriptions savedSub = subscriptionService.createSubscription(
                sub.getUserId(),
                sub.getType(),
                sub.getDiscountPercentage()
        );
        return ResponseEntity.ok(savedSub);
    }
}
