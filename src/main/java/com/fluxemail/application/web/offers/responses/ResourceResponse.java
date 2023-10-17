package com.fluxemail.application.web.offers.responses;

import com.fluxemail.application.core.offers.models.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceResponse {
    private Long id;
    private ResourceType resourceType;
    private String content;
}
