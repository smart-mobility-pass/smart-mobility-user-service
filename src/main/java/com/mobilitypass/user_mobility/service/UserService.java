package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.UserProfile;
import com.mobilitypass.user_mobility.dto.UserMobilitySummaryDTO;
import com.mobilitypass.user_mobility.dto.UserProfileDTO;

public interface UserService {
    UserProfile createProfile(UserProfileDTO dto);

    UserProfile getUser(String keycloakId);

    UserProfile getOrCreateProfile(String userId,String email,String name);


    UserMobilitySummaryDTO getSummary(String keycloakId);
}
