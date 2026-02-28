package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.Subscriptions;
import com.mobilitypass.user_mobility.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implémentation du service Subscription.
 * Gère le cycle de vie des abonnements de transport.
 */
@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepository subRepository;

    @Override
    public Subscriptions createSubscription(String userId, String type, Double discount) {
        Subscriptions sub = new Subscriptions();
        sub.setUserId(userId);
        sub.setSubscriptionType(type);
        sub.setDiscountPercentage(discount);
        sub.setStartDate(LocalDateTime.now());
        sub.setEndDate(LocalDateTime.now().plusMonths(1)); // Abonnement mensuel par défaut
        sub.setStatus("ACTIVE");
        sub.setApplicableTransport("ALL"); // Defaulting to ALL if not specified
        return subRepository.save(sub);
    }

    @Override
    public List<Subscriptions> getActiveSubscriptions(String userId) {
        return subRepository.findByUserIdAndStatus(userId, "ACTIVE");
    }
}