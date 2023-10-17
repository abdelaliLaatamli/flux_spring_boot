package com.fluxemail.application.core.offers.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeDto {
    private Long id;
    private String cid;
    private String url;
    private String offerImage;
    private String unsubscribeImage;

}
