package com.mobilitypass.user_mobility.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Subscriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String type;
    private Double discountPercentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;

    public Subscriptions(Long userId, String type, Double discountPercentage, LocalDateTime startDate, LocalDateTime endDate) {
        this.userId = userId;
        this.type = type;
        this.discountPercentage = discountPercentage;
        this.startDate = startDate;
        this.endDate = endDate;

    }
}
