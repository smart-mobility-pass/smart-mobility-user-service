package com.mobilitypass.user_mobility.service;

import com.mobilitypass.user_mobility.beans.MobilityPass;
import com.mobilitypass.user_mobility.enums.PassStatus;

import java.util.List;

public interface PassService {
    MobilityPass activatePass(String userId);

    MobilityPass activatePassFromOffer(String userId, Long offerId);

    void changePassStatus(String userId, PassStatus status);

    MobilityPass getUserPass(String userId);

    List<MobilityPass> getAllPasses();
}
