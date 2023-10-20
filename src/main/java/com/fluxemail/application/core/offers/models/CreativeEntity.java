package com.fluxemail.application.core.offers.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_creatives",schema = "_affiliate")
@Where(clause = "is_active = true")
public class CreativeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cid;

    @Column
    private String url;

    @Column
    private String offerImage;

    @Column
    private String unsubscribeImage;

    @Column(nullable = false)
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name="offer_id", nullable=false)
    private OfferEntity offer;

}
