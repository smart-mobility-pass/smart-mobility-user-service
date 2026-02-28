package com.mobilitypass.user_mobility.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserMobilitySummaryDTO {
    private String keycloakId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean hasActivePass;
    private String passType;
    private String passStatus;
    private Double dailyCap;
    private Double currentSpent;
    private Double activeDiscountRate;
}
