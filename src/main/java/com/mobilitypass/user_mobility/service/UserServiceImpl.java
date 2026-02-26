package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.MobilityPass;
import com.mobilitypass.user_mobility.beans.User;
import com.mobilitypass.user_mobility.dto.UserRegistrationDTO;
import com.mobilitypass.user_mobility.repository.MobilityPassRepository;
import com.mobilitypass.user_mobility.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MobilityPassRepository passRepository;

    @Override
    public User registerUser(UserRegistrationDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        user.setRole("CLIENT");
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public MobilityPass activatePass(Long userId) {
        return passRepository.findByUserId(userId).orElseGet(() -> {
            MobilityPass newPass = new MobilityPass();
            newPass.setUserId(userId);
            newPass.setStatus("ACTIVE");
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
}
