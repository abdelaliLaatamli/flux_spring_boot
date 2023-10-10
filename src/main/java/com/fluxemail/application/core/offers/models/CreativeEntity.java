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
//@Table(name="_offers",schema="_networks_schema")
@Table(name="_creatives",schema = "_affiliate")
public class CreativeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String cid;

    private String url;

    private String offer_image;

    private String unsub_image;

    private boolean is_active = false;

    @ManyToOne
    @JoinColumn(name="offer_id", nullable=false)
    private OfferEntity offer;

}
