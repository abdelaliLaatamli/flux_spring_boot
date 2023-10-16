package com.fluxemail.application.web.offers.requests;

import lombok.Data;

@Data
public class CreativeRequest {

    private String cid;
    private String url;
    private String offerImage;
    private String unsubscribeImage;

}
