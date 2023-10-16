package com.fluxemail.application.web.offers.requests;

import com.fluxemail.application.core.offers.models.Priority;
import lombok.Data;
import java.sql.Date;

@Data
public class SuppressionRequest {

    private String suppression_link;
    private Boolean enableEmpty;
    private Priority priority;

}
