package com.fluxemail.application.web.networks.responses;

import com.fluxemail.application.core.Networks.dtos.NetworkDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetworkAccountResponse {

    private Long id;
    private Integer accountAffiliateId;
    private String accountUsername;
    private String accountPassword;
    private String accountApiKey;
    private NetworkDto network;
}
