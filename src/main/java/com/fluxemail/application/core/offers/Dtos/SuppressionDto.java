package com.fluxemail.application.core.offers.Dtos;

import com.fluxemail.application.core.offers.models.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuppressionDto {
    private Long id ;
    private String suppression_link;
    private Boolean enableEmpty;
    private Priority priority;
}
