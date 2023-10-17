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
    private Integer account_Id;
    private String account_username;
    private String account_password;
    private String account_apikey;
    private NetworkDto network;
}
