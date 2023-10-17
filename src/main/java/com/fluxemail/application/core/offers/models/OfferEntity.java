package com.fluxemail.application.core.offers.models;

import com.fluxemail.application.core.Networks.models.NetworkAccountEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_offers",schema = "_affiliate")
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long sid;

    @Column
    private Integer campaignId;

    @Column(nullable = false , columnDefinition = "default 'ACTIVE'")
    @Enumerated(EnumType.STRING )
    private OfferStatus status = OfferStatus.ACTIVE;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String type;

    @Column(nullable = false,columnDefinition = "default 0")
    private Double amount;

    @Column(nullable = false)
    private String offerUrl;

    @Column
    private String unsubscribeUrl;

    @Column
    private String countries;


    @OneToOne(fetch = FetchType.LAZY)
    private NetworkAccountEntity networkAccount;

    @OneToOne
    private SuppressionEntity suppression;

    @OneToMany(mappedBy="offer",fetch = FetchType.LAZY)
    private Set<CreativeEntity> creatives;

    @OneToMany(mappedBy="offer",fetch = FetchType.LAZY)
    private Set<ResourceEntity> resources;

}
