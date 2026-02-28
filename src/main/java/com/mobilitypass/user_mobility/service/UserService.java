package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.UserProfile;
import com.mobilitypass.user_mobility.dto.UserMobilitySummaryDTO;
import com.mobilitypass.user_mobility.dto.UserProfileDTO;

import org.springframework.security.oauth2.jwt.Jwt;

public interface UserService {
    UserProfile createProfile(UserProfileDTO dto);

    UserProfile getUser(String keycloakId);

    UserProfile getOrCreateProfile(Jwt jwt);

    UserMobilitySummaryDTO getSummary(String keycloakId);
}
