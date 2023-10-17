package com.fluxemail.application.core.offers.models;

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
@Table(name="_resources",schema = "_affiliate")
public class ResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    private String content;

    @ManyToOne
    @JoinColumn(name="offer_id", nullable=false)
    private OfferEntity offer;

}
