package com.mobilitypass.user_mobility.repository;

import com.mobilitypass.user_mobility.beans.MobilityPass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MobilityPassRepository extends JpaRepository<MobilityPass, Long> {
    Optional<MobilityPass> findByUserId(String userId);
}
