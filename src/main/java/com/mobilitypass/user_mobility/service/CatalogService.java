package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.PassOffer;
import com.mobilitypass.user_mobility.beans.SubscriptionOffer;

import java.util.List;

public interface CatalogService {
    // Subscription Offers
    List<SubscriptionOffer> getAllSubscriptionOffers();

    SubscriptionOffer createSubscriptionOffer(SubscriptionOffer offer);

    SubscriptionOffer updateSubscriptionOffer(Long id, SubscriptionOffer offer);

    void deleteSubscriptionOffer(Long id);

    // Pass Offers
    List<PassOffer> getAllPassOffers();

    PassOffer createPassOffer(PassOffer offer);

    PassOffer updatePassOffer(Long id, PassOffer offer);

    void deletePassOffer(Long id);
}
