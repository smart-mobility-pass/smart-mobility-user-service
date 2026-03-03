package com.mobilitypass.user_mobility.controller;

import com.mobilitypass.user_mobility.beans.PassOffer;
import com.mobilitypass.user_mobility.beans.SubscriptionOffer;
import com.mobilitypass.user_mobility.service.CatalogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    // Subscription Offers
    @GetMapping("/subscription-offers")
    public ResponseEntity<List<SubscriptionOffer>> getSubscriptionOffers() {
        return ResponseEntity.ok(catalogService.getAllSubscriptionOffers());
    }

    @PostMapping("/subscription-offers")
    public ResponseEntity<SubscriptionOffer> createSubscriptionOffer(@RequestBody SubscriptionOffer offer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogService.createSubscriptionOffer(offer));
    }

    @PutMapping("/subscription-offers/{id}")
    public ResponseEntity<SubscriptionOffer> updateSubscriptionOffer(@PathVariable Long id,
            @RequestBody SubscriptionOffer offer) {
        return ResponseEntity.ok(catalogService.updateSubscriptionOffer(id, offer));
    }

    @DeleteMapping("/subscription-offers/{id}")
    public ResponseEntity<Void> deleteSubscriptionOffer(@PathVariable Long id) {
        catalogService.deleteSubscriptionOffer(id);
        return ResponseEntity.noContent().build();
    }

    // Pass Offers
    @GetMapping("/pass-offers")
    public ResponseEntity<List<PassOffer>> getPassOffers() {
        return ResponseEntity.ok(catalogService.getAllPassOffers());
    }

    @PostMapping("/pass-offers")
    public ResponseEntity<PassOffer> createPassOffer(@RequestBody PassOffer offer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogService.createPassOffer(offer));
    }

    @PutMapping("/pass-offers/{id}")
    public ResponseEntity<PassOffer> updatePassOffer(@PathVariable Long id, @RequestBody PassOffer offer) {
        return ResponseEntity.ok(catalogService.updatePassOffer(id, offer));
    }

    @DeleteMapping("/pass-offers/{id}")
    public ResponseEntity<Void> deletePassOffer(@PathVariable Long id) {
        catalogService.deletePassOffer(id);
        return ResponseEntity.noContent().build();
    }
}
