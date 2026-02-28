package com.mobilitypass.user_mobility.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Représente le pass de mobilité d'un utilisateur.
 * Gère le statut du pass et le suivi de la consommation journalière (Daily
 * Cap).
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobilityPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId; // keycloakId
    private String status; // ACTIVE, SUSPENDED
    private String passType; // STANDARD, PREMIUM
    private Double dailyCapAmount;
    private Double todaySpentAmount;
    private LocalDate capResetDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;

    public MobilityPass(String userId, String status, String passType, Double dailyCapAmount) {
        this.userId = userId;
        this.status = status;
        this.passType = passType;
        this.dailyCapAmount = dailyCapAmount;
        this.todaySpentAmount = 0.0;
        this.capResetDate = LocalDate.now();
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now().plusYears(1); // Défaut 1 an
        this.createdAt = LocalDateTime.now();
    }
}
