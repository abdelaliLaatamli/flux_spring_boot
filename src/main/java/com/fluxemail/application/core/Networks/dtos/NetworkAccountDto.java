package com.fluxemail.application.core.Networks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetworkAccountDto {

    private Long id;
    private Integer accountAffiliateId;
    private String accountUsername;
    private String accountPassword;
    private String accountApiKey;
    private NetworkDto network;

}
