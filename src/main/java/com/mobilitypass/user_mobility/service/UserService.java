package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.MobilityPass;
import com.mobilitypass.user_mobility.beans.User;
import com.mobilitypass.user_mobility.dto.UserRegistrationDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UserService {
    // Gestion Utilisateur
    User registerUser(UserRegistrationDTO dto);
    Optional<User> getUserById(Long id);
    MobilityPass activatePass(Long userId);
    void changePassStatus(Long userId, String status);
}
