package com.fluxemail.application.core.Networks.models;

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
@Table(name = "_networkaccounts" , schema = "_affiliate")
public class NetworkAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer accountAffiliateId;
    @Column(nullable = true)
    private String accountUsername;
    @Column(nullable = true)
    private String accountPassword;
    @Column(nullable = true , columnDefinition = "TEXT")
    private String accountApiKey;

    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name="network_id", nullable=false)
    private NetworkEntity network;

}
