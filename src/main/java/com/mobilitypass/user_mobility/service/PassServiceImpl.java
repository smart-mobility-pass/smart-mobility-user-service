package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.MobilityPass;
import com.mobilitypass.user_mobility.enums.PassStatus;
import com.mobilitypass.user_mobility.enums.PassType;
import com.mobilitypass.user_mobility.error.ResourceNotFoundException;
import com.mobilitypass.user_mobility.repository.MobilityPassRepository;
import com.mobilitypass.user_mobility.repository.PassOfferRepository;
import com.mobilitypass.user_mobility.beans.PassOffer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation du service MobilityPass.
 * Assure le suivi de la validité du pass et de la consommation journalière de
 * l'utilisateur.
 */
@Service
@Transactional
public class PassServiceImpl implements PassService {
    @Autowired
    private MobilityPassRepository passRepository;

    @Autowired
    private PassOfferRepository passOfferRepository;

    @Override
    public MobilityPass activatePass(String userId) {
        return passRepository.findByUserId(userId).orElseGet(() -> {
            MobilityPass newPass = new MobilityPass(userId, PassStatus.ACTIVE, PassType.STANDARD, 1200.0);
            return passRepository.save(newPass);
        });
    }

    @Override
    public MobilityPass activatePassFromOffer(String userId, Long offerId) {
        PassOffer offer = passOfferRepository.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Pass offer not found: " + offerId));

        // If user already has a pass, update it to the new offer
        return passRepository.findByUserId(userId).map(existing -> {
            existing.setOfferId(offer.getId());
            existing.setPassType(offer.getPassType());
            existing.setDailyCapAmount(offer.getDailyCapAmount());
            existing.setStatus(PassStatus.ACTIVE);
            existing.setStartDate(java.time.LocalDateTime.now());
            existing.setEndDate(java.time.LocalDateTime.now().plusDays(offer.getValidityDays()));
            return passRepository.save(existing);
        }).orElseGet(() -> {
            MobilityPass newPass = new MobilityPass();
            newPass.setUserId(userId);
            newPass.setOfferId(offer.getId());
            newPass.setPassType(offer.getPassType());
            newPass.setDailyCapAmount(offer.getDailyCapAmount());
            newPass.setStatus(PassStatus.ACTIVE);
            newPass.setStartDate(java.time.LocalDateTime.now());
            newPass.setEndDate(java.time.LocalDateTime.now().plusDays(offer.getValidityDays()));
            return passRepository.save(newPass);
        });
    }

    @Override
    public void changePassStatus(String userId, PassStatus status) {
        MobilityPass pass = passRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Pass non trouvé pour : " + userId));
        pass.setStatus(status);
        passRepository.save(pass);
    }   

    @Override
    public MobilityPass getUserPass(String userId) {
        return passRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun pass trouvé pour cet utilisateur : " + userId));
    }

    @Override
    public List<MobilityPass> getAllPasses() {
        return passRepository.findAll();
    }
}
