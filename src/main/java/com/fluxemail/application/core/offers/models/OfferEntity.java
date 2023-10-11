package com.fluxemail.application.core.offers.models;

import com.fluxemail.application.core.Networks.NetworkAccount;
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
//@Table(name="_offers",schema="_networks_schema")
@Table(name="_offers",schema = "_affiliate")
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

    private String offerUrl;

    private String unsubUrl;


    @OneToOne(fetch = FetchType.LAZY)
    private NetworkAccount networkAccount;

    //    @OneToOne(optional = true)
    //    private SuppressionEntity suppression;

    @OneToMany(mappedBy="offer",fetch = FetchType.LAZY)
    private Set<CreativeEntity> creatives;

    @OneToMany(mappedBy="offer",fetch = FetchType.LAZY)
    private Set<ResourcesEntity> resources;

}
