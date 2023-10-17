package com.fluxemail.application.web.networks.responses;

import com.fluxemail.application.core.Networks.dtos.NetworkAccountDto;
import com.fluxemail.application.core.Networks.models.NetworkType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetworkResponse {
    private Integer id;
    private String name;
    private String networkUrl;
    private String networkApiUrl;
    private NetworkType networkType;
    private Set<NetworkAccountDto> networkAccounts;
}
