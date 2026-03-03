package com.mobilitypass.user_mobility.beans;

import com.mobilitypass.user_mobility.enums.SubscriptionType;
import com.mobilitypass.user_mobility.enums.TransportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modèle d'abonnement disponible dans le catalogue.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType; // e.g., MONTHLY, WEEKLY
    @Enumerated(EnumType.STRING)
    private TransportType applicableTransport; // e.g., BUS, TER, ALL
    private Double discountPercentage;
    private Double price;
    private Integer validityDays;
    private boolean active = true;
}
