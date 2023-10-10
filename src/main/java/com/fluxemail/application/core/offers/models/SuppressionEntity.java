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
//@Table(name="_offers",schema="_networks_schema")
@Table(name="_suppression", schema = "_affiliate")
public class SuppressionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String suppression_link;

    private Boolean is_updated;

    private Boolean enableEmpty;

    private Date last_updated;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "offer_id")
    private OfferEntity offer;

}
