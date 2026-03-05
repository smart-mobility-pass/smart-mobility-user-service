package com.mobilitypass.user_mobility.proxy;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "billing-service")
public interface BillingProxy {

    @PostMapping("/accounts")
    void createAccount(@RequestBody CreateAccountRequest request);

    @PostMapping("/accounts/{userId}/charge")
    void charge(@PathVariable String userId, @RequestBody ChargeRequest request);

    record CreateAccountRequest(String userId, String currency) {
    }

    record ChargeRequest(Double amount, String description) {
    }
}
