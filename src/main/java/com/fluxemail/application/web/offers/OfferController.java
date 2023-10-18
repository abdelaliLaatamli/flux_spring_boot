package com.fluxemail.application.web.offers;

import com.fluxemail.application.core.offers.Dtos.OfferDto;
import com.fluxemail.application.core.offers.models.OfferEntity;
import com.fluxemail.application.web.offers.requests.OfferRequest;
import com.fluxemail.application.core.offers.services.OfferService;
import com.fluxemail.application.web.offers.responses.OfferResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/offers")
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @GetMapping
    private ResponseEntity<List<?>> getOffers(){
        List<OfferDto> offers = offerService.getOffers();

        var offerResponses = offers
                .stream()
                .map( offerDto -> modelMapper.map(offerDto , OfferResponse.class) )
                .collect(Collectors.toList());

        return ResponseEntity.ok( offerResponses );
    }

    @GetMapping("/{offerId}")
    private ResponseEntity<OfferResponse> getOffer(@PathVariable(value = "offerId") Long offerId){
        var offer = offerService.getOffer( offerId );

        var offerResponse = modelMapper.map( offer , OfferResponse.class );

        return ResponseEntity.ok( offerResponse );
    }

    @PostMapping("")
    private ResponseEntity<OfferResponse> addPost(@RequestBody OfferRequest offer){

        OfferDto offerDto = modelMapper.map( offer , OfferDto.class );

        OfferDto createdOffer = offerService.createOffer( offer.getNetworkAccountId() , offerDto);

        var offerResponse = this.modelMapper.map(createdOffer , OfferResponse.class );

        return ResponseEntity.ok(offerResponse);
    }

    @PutMapping("/{offerId}")
    private ResponseEntity<String> putOffer(@PathVariable(value = "offerId") Long offerId){
        return ResponseEntity.ok(" Put Offer details " + offerId);
    }

    @DeleteMapping("/{offerId}")
    private ResponseEntity<Void> deleteOffer(@PathVariable(value = "offerId") Long offerId){
        return ResponseEntity.noContent().build();
    }

}
