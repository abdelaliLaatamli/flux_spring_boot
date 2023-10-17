package com.fluxemail.application.core.offers.Dtos;

import com.fluxemail.application.core.offers.models.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto {

    private Long id;
    private ResourceType resourceType;
    private String content;

}
