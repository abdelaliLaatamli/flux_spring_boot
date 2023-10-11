package com.fluxemail.application.core.offers.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_suppression", schema = "_affiliate")
public class SuppressionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String suppression_link;

    @Column(nullable = false)
    private Boolean is_updated=false;


    @Column(nullable = false)
    private Boolean enableEmpty=false;

    private Date last_updated;

    @Enumerated(EnumType.STRING)
    private Priority priority=Priority.MEDUIM;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "offer_id")
    private OfferEntity offer;

}
