package com.mobilitypass.user_mobility.beans;

import com.mobilitypass.user_mobility.enums.PassStatus;
import com.mobilitypass.user_mobility.enums.PassType;
import jakarta.persistence.*;
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
    private Long offerId; // Link to Catalog (PassOffer)
    private String userId; // keycloakId
    @Enumerated(EnumType.STRING)
    private PassStatus status;// ACTIVE, SUSPENDED
    @Enumerated(EnumType.STRING)
    private PassType passType; // STANDARD, PREMIUM
    private Double dailyCapAmount;
    private LocalDate capResetDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;

    public MobilityPass(String userId, PassStatus status, PassType passType, Double dailyCapAmount) {
        this.userId = userId;
        this.status = status;
        this.passType = passType;
        this.dailyCapAmount = dailyCapAmount;
        this.capResetDate = LocalDate.now();
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now().plusYears(1); // Défaut 1 an
        this.createdAt = LocalDateTime.now();
    }
}
