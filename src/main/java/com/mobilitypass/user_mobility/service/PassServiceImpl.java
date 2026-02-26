package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.MobilityPass;
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
        MobilityPass pass = passRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Pass non trouvé"));
        pass.setStatus(status.toUpperCase());
        passRepository.save(pass);
    }




    @Override
    public Optional<MobilityPass> getPassByUserId(Long userId) {
        return passRepository.findByUserId(userId);
    }

    @Override
    public List<MobilityPass> getAllPasses() {
        return passRepository.findAll();
    }
}
