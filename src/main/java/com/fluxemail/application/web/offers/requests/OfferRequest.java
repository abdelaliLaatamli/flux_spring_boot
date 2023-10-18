package com.fluxemail.application.web.offers.requests;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;


@Data
public class OfferRequest {

    @NotNull(message = "SID Can not be null")
    @Min(value = 1 , message = "SID can not be less than 1")
    private Long sid;

    private Integer campaignId;

    @NotBlank(message = "name can not be black")
    @NotEmpty(message = "name can not be empty")
    private String name;

    private String description;

    private String type;

    private Double amount;

    @NotBlank(message = "offerUrl can not be black")
    @NotEmpty(message = "offerUrl can not be empty")
    private String offerUrl;

    @NotBlank(message = "unsubscribeUrl can not be black")
    @NotEmpty(message = "unsubscribeUrl can not be empty")
    private String unsubscribeUrl;

    @NotBlank(message = "countries can not be black")
    @NotEmpty(message = "countries can not be empty")
    private String countries;


    @NotNull(message = "networkAccountId Can not be null")
    @Min(value = 1 , message = "networkAccountId can not be less than 1")
    private Long networkAccountId;

    private Set<ResourceRequest> resources;
    private Set<CreativeRequest> creatives;
    private SuppressionRequest suppression;

}
