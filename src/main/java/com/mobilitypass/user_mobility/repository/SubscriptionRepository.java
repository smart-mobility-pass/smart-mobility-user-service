package com.mobilitypass.user_mobility.repository;

import com.mobilitypass.user_mobility.beans.MobilityPass;
import com.mobilitypass.user_mobility.beans.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscriptions, Long> {

    List<Subscriptions> findByUserId(Long userId);}
