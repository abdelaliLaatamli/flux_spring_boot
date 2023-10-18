package com.fluxemail.application.web.networks.requests;

import com.fluxemail.application.core.Networks.models.NetworkType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetworkRequest {

    @NotEmpty(message = "Name may not be empty")
    @Size(min = 2, max = 60, message = "Name must be between 2 and 32 characters long")
    private String name;

    private String networkUrl;
    private String networkApiUrl;

    @NotNull
    @Enumerated
    private NetworkType networkType;

}
