package com.fluxemail.application.core.offers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_offers" , schema = "_schema_networks")
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sid;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String type;

    private Double amount;

//    @OneToOne
//    private NetworkEntity network;
//
//    @OneToOne(optional = true)
//    private SuppressionEntity suppression;
}
