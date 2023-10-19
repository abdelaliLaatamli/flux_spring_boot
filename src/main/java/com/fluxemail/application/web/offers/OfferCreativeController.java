package com.fluxemail.application.web.offers;

import com.fluxemail.application.core.offers.services.CreativeOfferService;
import com.fluxemail.application.core.offers.services.FileService;
import com.fluxemail.application.web.offers.requests.CreativeRequest;
import com.fluxemail.application.web.offers.responses.CreativeResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/offers/{offerId}/creatives")
@AllArgsConstructor
public class OfferCreativeController {

    private final CreativeOfferService creativeOfferService;
    private final FileService fileService;
    private final ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity<List<CreativeResponse>> getCreatives(@PathVariable long offerId){
        var offerCreatives = creativeOfferService.getAllCreatives( offerId );
        var offerCreativesResponse = offerCreatives
                .stream()
                .map( offerCreativeDto -> modelMapper.map( offerCreativeDto , CreativeResponse.class) )
                .collect(Collectors.toList());
        return ResponseEntity.ok(offerCreativesResponse );
    }

    @GetMapping("/{creativeId}")
    public ResponseEntity<String> getCreative (
            @PathVariable long offerId ,
            @PathVariable long creativeId
    ){
        return ResponseEntity.ok("offer id " + offerId + " off creative id " +creativeId+" ");
    }

    @PostMapping
    public ResponseEntity<CreativeResponse> addCreative(
            @PathVariable long offerId ,
            @RequestParam("offerImage") MultipartFile offerImage,
            @RequestParam("unsubscribeImage") MultipartFile unsubscribeImage,
            @RequestParam(value = "cid" , required = false) String cid,
            @RequestParam(value = "url" , required = false) String url
    ){
        var creative = CreativeRequest
                .builder()
                .offerImage(offerImage)
                .unsubscribeImage(unsubscribeImage)
                .cid(cid)
                .url(url)
                .build();

        var createdCreativeDto = creativeOfferService.addCreative( offerId , creative );
        var createdCreativeResponse = modelMapper.map( createdCreativeDto , CreativeResponse.class );

        return ResponseEntity
                .created(URI.create("/api/v1/offers/"+offerId+"/creatives"+createdCreativeResponse.getId()))
                .body(createdCreativeResponse);
    }


    @PutMapping("/{creativeId}")
    public ResponseEntity<String> updateCreative(
            @PathVariable long offerId ,
            @PathVariable long creativeId,
            @RequestBody CreativeRequest creativeRequest
    ){
        return ResponseEntity.accepted().body(" offerId " + offerId + " creative id " + creativeId );
    }

    @DeleteMapping("/{creativeId}")
    public ResponseEntity<String> deleteCreative(
            @PathVariable long offerId ,
            @PathVariable long creativeId
    ){
        return ResponseEntity.accepted().body(" offerId " + offerId + " creative id " + creativeId );
    }

}
