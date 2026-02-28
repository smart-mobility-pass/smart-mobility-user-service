package com.mobilitypass.user_mobility.repository;

import com.mobilitypass.user_mobility.beans.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    Optional<UserProfile> findByEmail(String email);

    Optional<UserProfile> findByKeycloakId(String keycloakId);
}
