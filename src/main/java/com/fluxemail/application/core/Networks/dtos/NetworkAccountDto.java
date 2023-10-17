package com.fluxemail.application.core.Networks.dtos;

import com.fluxemail.application.core.Networks.models.NetworkEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetworkAccountDto {

    private Long id;
    private Integer account_Id;
    private String account_username;
    private String account_password;
    private String account_apikey;
    private NetworkDto network;

}
