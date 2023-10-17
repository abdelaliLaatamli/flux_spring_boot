package com.fluxemail.application.web.offers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeResponse {

    private String id;
    private String cid;
    private String url;
    private String offerImage;
    private String unsubscribeImage;

}
