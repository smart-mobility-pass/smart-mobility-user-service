package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.PassOffer;
import com.mobilitypass.user_mobility.beans.SubscriptionOffer;
import com.mobilitypass.user_mobility.repository.PassOfferRepository;
import com.mobilitypass.user_mobility.repository.SubscriptionOfferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final SubscriptionOfferRepository subscriptionOfferRepository;
    private final PassOfferRepository passOfferRepository;

    @Override
    public List<SubscriptionOffer> getAllSubscriptionOffers() {
        return subscriptionOfferRepository.findAll();
    }

    @Override
    public SubscriptionOffer createSubscriptionOffer(SubscriptionOffer offer) {
        return subscriptionOfferRepository.save(offer);
    }

    @Override
    public SubscriptionOffer updateSubscriptionOffer(Long id, SubscriptionOffer offer) {
        return subscriptionOfferRepository.findById(id)
                .map(existingOffer -> {
                    existingOffer.setName(offer.getName());
                    existingOffer.setDescription(offer.getDescription());
                    existingOffer.setSubscriptionType(offer.getSubscriptionType());
                    existingOffer.setApplicableTransport(offer.getApplicableTransport());
                    existingOffer.setDiscountPercentage(offer.getDiscountPercentage());
                    existingOffer.setPrice(offer.getPrice());
                    existingOffer.setValidityDays(offer.getValidityDays());
                    existingOffer.setActive(offer.isActive());
                    return subscriptionOfferRepository.save(existingOffer);
                }).orElseThrow(() -> new RuntimeException("Subscription offer not found"));
    }

    @Override
    public void deleteSubscriptionOffer(Long id) {
        subscriptionOfferRepository.deleteById(id);
    }

    @Override
    public List<PassOffer> getAllPassOffers() {
        return passOfferRepository.findAll();
    }

    @Override
    public PassOffer createPassOffer(PassOffer offer) {
        return passOfferRepository.save(offer);
    }

    @Override
    public PassOffer updatePassOffer(Long id, PassOffer offer) {
        return passOfferRepository.findById(id)
                .map(existingOffer -> {
                    existingOffer.setName(offer.getName());
                    existingOffer.setDescription(offer.getDescription());
                    existingOffer.setPassType(offer.getPassType());
                    existingOffer.setDailyCapAmount(offer.getDailyCapAmount());
                    existingOffer.setPrice(offer.getPrice());
                    existingOffer.setValidityDays(offer.getValidityDays());
                    existingOffer.setActive(offer.isActive());
                    return passOfferRepository.save(existingOffer);
                }).orElseThrow(() -> new RuntimeException("Pass offer not found"));
    }

    @Override
    public void deletePassOffer(Long id) {
        passOfferRepository.deleteById(id);
    }
}
