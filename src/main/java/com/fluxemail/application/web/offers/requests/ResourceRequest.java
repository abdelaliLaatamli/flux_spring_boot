package com.fluxemail.application.web.offers.requests;

import com.fluxemail.application.core.offers.models.ResourceType;
import lombok.Data;

@Data
public class ResourceRequest {
    private ResourceType resourceType;
    private String content;
}
