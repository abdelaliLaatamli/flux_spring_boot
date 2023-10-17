package com.fluxemail.application.web.offers.responses;

import com.fluxemail.application.web.offers.requests.CreativeRequest;
import com.fluxemail.application.web.offers.requests.ResourceRequest;
import com.fluxemail.application.web.offers.requests.SuppressionRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferResponse {
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
    private Set<ResourceResponse> resources;
    private Set<CreativeResponse> creatives;
    private SuppressionResponse suppression;
}
