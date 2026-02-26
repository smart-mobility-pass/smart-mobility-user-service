package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.MobilityPass;

import java.util.List;
import java.util.Optional;

public interface PassService {
    MobilityPass activatePass(Long userId);
    void changePassStatus(Long userId, String status);
    Optional<MobilityPass> getPassByUserId(Long userId);
    List<MobilityPass> getAllPasses();
}
