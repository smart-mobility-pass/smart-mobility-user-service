package com.mobilitypass.user_mobility.repository;

import com.mobilitypass.user_mobility.beans.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscriptions, Long> {
    List<Subscriptions> findByUserId(String userId);

    List<Subscriptions> findByUserIdAndStatus(String userId, String status);
}
