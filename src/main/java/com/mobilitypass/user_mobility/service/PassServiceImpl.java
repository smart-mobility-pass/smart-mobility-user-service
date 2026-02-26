package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.MobilityPass;
import com.mobilitypass.user_mobility.error.ResourceNotFoundException;
import com.mobilitypass.user_mobility.repository.MobilityPassRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PassServiceImpl implements PassService {
    @Autowired
    private MobilityPassRepository passRepository;

    @Override
    public MobilityPass activatePass(Long userId) {
        return passRepository.findByUserId(userId).orElseGet(() -> {
            MobilityPass newPass = new MobilityPass();
            newPass.setUserId(userId);
            newPass.setStatus("ACTIVE");
            newPass.setDailyCapAmount(2500.0);
            newPass.setCreatedAt(LocalDateTime.now());
            return passRepository.save(newPass);
        });
    }

    @Override
    public void changePassStatus(Long userId, String status) {
        // CORRECTION : On utilise ton exception au lieu de RuntimeException
        MobilityPass pass = passRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Pass de mobilité non trouvé pour l'utilisateur : " + userId));
        pass.setStatus(status.toUpperCase());
        passRepository.save(pass);
    }




    @Override
    public MobilityPass getPassByUserId(Long userId) {
        // CORRECTION : Retour direct de l'objet ou erreur 404
        return passRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun pass trouvé pour cet utilisateur"));
    }

    @Override
    public List<MobilityPass> getAllPasses() {
        return passRepository.findAll();
    }
}
