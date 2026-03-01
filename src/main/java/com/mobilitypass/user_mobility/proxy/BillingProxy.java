package com.mobilitypass.user_mobility.proxy;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "billing-service")
public interface BillingProxy {

    @PostMapping("/accounts")
    void createAccount(@RequestBody CreateAccountRequest request);

    record CreateAccountRequest(String userId, String currency) {
    }
}
