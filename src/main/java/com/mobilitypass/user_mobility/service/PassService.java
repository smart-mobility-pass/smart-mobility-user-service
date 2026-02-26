package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.MobilityPass;

import java.util.List;

public interface PassService {
    MobilityPass activatePass(Long userId);
    void changePassStatus(Long userId, String status);
    MobilityPass getPassByUserId(Long userId);
    List<MobilityPass> getAllPasses();
}
