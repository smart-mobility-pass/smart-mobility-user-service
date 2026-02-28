package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.Subscriptions;
import java.util.List;

public interface SubscriptionService {
    Subscriptions createSubscription(String userId, String type, Double discount);

    List<Subscriptions> getActiveSubscriptions(String userId);
}
