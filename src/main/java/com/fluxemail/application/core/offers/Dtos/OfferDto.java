package com.fluxemail.application.core.offers.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {
    private Long id;
    private Long sid;
    private Integer campaignId;
    private String name;
    private String description;
    private String type;
    private Double amount;
    private String offerUrl;
    private String unsubscribeUrl;
    private String countries;
    private Long networkAccountId;
    private Set<ResourceDto> resources;
    private Set<CreativeDto> creatives;
    private SuppressionDto suppression;
}

