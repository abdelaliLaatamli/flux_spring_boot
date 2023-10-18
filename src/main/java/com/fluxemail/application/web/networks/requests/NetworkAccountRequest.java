package com.fluxemail.application.web.networks.requests;

import com.fluxemail.application.web.networks.responses.NetworkResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetworkAccountRequest {
    private Integer accountAffiliateId;
    private String accountUsername;
    private String accountPassword;
    private String accountApiKey;
    private Long networkId;
}
