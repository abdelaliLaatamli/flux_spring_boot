package com.fluxemail.application.core.offers.Dtos;

import com.fluxemail.application.core.Networks.dtos.NetworkAccountDto;
import com.fluxemail.application.core.Networks.models.NetworkAccountEntity;
import com.fluxemail.application.core.offers.models.OfferStatus;
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
    private OfferStatus status = OfferStatus.ACTIVE;
    private NetworkAccountDto networkAccount;
    private SuppressionDto suppression;
    private Set<CreativeDto> creatives;
    private Set<ResourceDto> resources;

}

