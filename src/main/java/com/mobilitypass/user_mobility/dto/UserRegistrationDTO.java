package com.mobilitypass.user_mobility.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
