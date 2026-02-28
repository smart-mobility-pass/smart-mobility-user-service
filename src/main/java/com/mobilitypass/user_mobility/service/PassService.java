package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.MobilityPass;
import java.util.List;

public interface PassService {
    MobilityPass activatePass(String userId);

    void changePassStatus(String userId, String status);

    MobilityPass getUserPass(String userId);

    void addSpentAmount(String userId, Double amount);

    List<MobilityPass> getAllPasses();
}
