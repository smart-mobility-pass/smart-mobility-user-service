package com.mobilitypass.user_mobility.controller;

import com.mobilitypass.user_mobility.dto.PricingContextDTO;
import com.mobilitypass.user_mobility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/users")
public class InternalUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/pricing-context")
    public PricingContextDTO getPricingContext(@PathVariable String userId) {
        return userService.getPricingContext(userId);
    }
}
