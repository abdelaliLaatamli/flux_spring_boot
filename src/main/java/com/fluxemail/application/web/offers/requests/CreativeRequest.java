package com.fluxemail.application.web.offers.requests;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class CreativeRequest {

    private MultipartFile offerImage;
    private MultipartFile unsubscribeImage;
    private String cid;
    private String url;

}
