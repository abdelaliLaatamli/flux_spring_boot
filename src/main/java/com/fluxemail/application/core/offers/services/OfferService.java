package com.fluxemail.application.core.offers.services;

import com.fluxemail.application.core.offers.models.OfferEntity;
import com.fluxemail.application.core.offers.repositories.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public List<OfferEntity> getOffers(){
        return offerRepository.findAll();
    }

    public OfferEntity getOffer(Long offerId) {
        return offerRepository.findById(offerId).orElseThrow(
                () -> new RuntimeException("Offer id " + offerId +" not found")
        );
    }
}
