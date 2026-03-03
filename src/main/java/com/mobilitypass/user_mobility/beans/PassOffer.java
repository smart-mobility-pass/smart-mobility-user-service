package com.mobilitypass.user_mobility.beans;

import com.mobilitypass.user_mobility.enums.PassType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modèle de pass mobilité disponible dans le catalogue.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private PassType passType; // e.g., STANDARD, PREMIUM, STUDENT
    private Double dailyCapAmount;
    private Double price;
    private Integer validityDays;
    private boolean active = true;
}
