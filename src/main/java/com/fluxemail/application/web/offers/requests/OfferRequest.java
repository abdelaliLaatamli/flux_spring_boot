package com.fluxemail.application.web.offers.requests;

import lombok.Data;

import java.util.Set;


@Data
public class OfferRequest {

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
    private Set<ResourceRequest> resources;
    private Set<CreativeRequest> creatives;
    private SuppressionRequest suppression;

}
