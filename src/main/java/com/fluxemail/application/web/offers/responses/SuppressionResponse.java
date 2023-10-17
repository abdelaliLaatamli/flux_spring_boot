package com.fluxemail.application.web.offers.responses;

import com.fluxemail.application.core.offers.models.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuppressionResponse {
    private Long id;
    private String suppression_link;
    private Boolean enableEmpty;
    private Priority priority;

}
