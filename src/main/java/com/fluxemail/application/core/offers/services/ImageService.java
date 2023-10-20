package com.fluxemail.application.core.offers.services;

import com.fluxemail.application.core.offers.repositories.CreativeRepository;
import com.fluxemail.application.shared.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageService {

    private final FileService fileService;
    private final CreativeRepository creativeRepository;
    public ResponseEntity<Resource> getImage(
            Long creativeId,
            Integer imageOrder
    ){

        var creative = creativeRepository
                .findActivatedById( creativeId )
                .orElseThrow( () -> new ResourceNotFoundException( String.format( "Creative Id %s not found !!" , creativeId ) ));

        var getStr = creative.getOfferImage();

        if( imageOrder == 2 )
            getStr = creative.getUnsubscribeImage();


        var imageName = getStr.replace("uploads\\", "");

        var imageExtention = getFileExtension(imageName);

        var image = fileService.load(imageName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFilename() + "\"")
                .contentType( imageExtention.equals( "jpg" ) ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG )
                .body(image);
    }

    private String getFileExtension( String fileName ){
        var fileNameExt = fileName.split("\\.");
        return fileNameExt[ fileNameExt.length - 1 ];
    }

}
