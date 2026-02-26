package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.Subscriptions;

import java.util.List;

public interface SubscriptionService {
    Subscriptions createSubscription(Long userId, String type, Double discount);

    List<Subscriptions> getUserSubscriptions(Long userId);

    boolean hasActiveSubscription(Long userId);
}
