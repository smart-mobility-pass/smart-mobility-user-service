package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.Subscriptions;
import com.mobilitypass.user_mobility.repository.SubscriptionRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscriptions createSubscription(Long userId, String type, Double discount) {
        Subscriptions sub = new Subscriptions();

        sub.setUserId(userId);
        sub.setType(type.toUpperCase());
        sub.setDiscountPercentage(discount);
        sub.setStartDate(LocalDateTime.now());
        sub.setEndDate(LocalDateTime.now().plusMonths(1)); // Abonnement mensuel par défaut
        sub.setStatus("ACTIVE");

        return subscriptionRepository.save(sub);
    }

    @Override
    public List<Subscriptions> getUserSubscriptions(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    @Override
    public boolean hasActiveSubscription(Long userId) {
        // Vérifie s'il existe au moins un abonnement ACTIVE dont la date de fin est dans le futur
        return subscriptionRepository.findByUserId(userId).stream()
                .anyMatch(s -> "ACTIVE".equals(s.getStatus()) && s.getEndDate().isAfter(LocalDateTime.now()));
    }
}