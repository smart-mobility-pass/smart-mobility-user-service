package com.mobilitypass.user_mobility.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobilityPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String status; // ACTIVE, SUSPENDED
    private Double dailyCapAmount;
    private LocalDateTime createdAt;

    public MobilityPass(Long userId, String status, Double dailyCapAmount) {
        this.userId = userId;
        this.status = status;
        this.dailyCapAmount = dailyCapAmount;
        this.createdAt = LocalDateTime.now();

    }
}
