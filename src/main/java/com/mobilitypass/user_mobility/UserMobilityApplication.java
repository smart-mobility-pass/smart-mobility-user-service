package com.mobilitypass.user_mobility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserMobilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserMobilityApplication.class, args);
    }

}
