package com.fluxemail.application.web.networks.requests;

import com.fluxemail.application.web.networks.responses.NetworkResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetworkAccountRequest {
    private Long id;
    private Integer account_Id;
    private String account_username;
    private String account_password;
    private String account_apikey;
    private NetworkResponse network;
}
