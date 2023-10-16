package com.fluxemail.application.web.offers;

import com.fluxemail.application.core.offers.models.OfferEntity;
import com.fluxemail.application.web.offers.requests.OfferRequest;
import com.fluxemail.application.core.offers.services.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offers")
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping
    private ResponseEntity<List<OfferEntity>> getOffers(){
        List<OfferEntity> offers = offerService.getOffers();
        return ResponseEntity.ok( offers );
    }

    @GetMapping("/{offerId}")
    private ResponseEntity<OfferEntity> getOffer(@PathVariable(value = "offerId") Long offerId){
        OfferEntity offer = offerService.getOffer( offerId );
        return ResponseEntity.ok( offer );
    }

    @PostMapping("")
    private ResponseEntity<OfferEntity> addPost(@RequestBody OfferRequest offer){

        System.out.println( offer );

        return ResponseEntity.ok(null);
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
