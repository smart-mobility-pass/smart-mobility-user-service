package com.mobilitypass.user_mobility.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
    private String keycloakId;
    private String email;
    private String firstName;
    private String lastName;
}
