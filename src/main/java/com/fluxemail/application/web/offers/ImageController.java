package com.fluxemail.application.web.offers;

import com.fluxemail.application.core.offers.services.FileService;
import com.fluxemail.application.core.offers.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/images")
@AllArgsConstructor
public class ImageController {

    private final FileService fileService;
    private final ImageService imageService;

    @GetMapping(value = "/{creativeId}/{imageOrder}" , produces="image/png")
    private ResponseEntity<Resource> getImage(
            @PathVariable Long creativeId ,
            @PathVariable Integer imageOrder
    ){
        var response = imageService.getImage( creativeId , imageOrder);

        return response;

    }

}
