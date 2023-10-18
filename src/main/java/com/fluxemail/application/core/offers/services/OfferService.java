package com.fluxemail.application.core.offers.services;

import com.fluxemail.application.core.offers.Dtos.OfferDto;
import com.fluxemail.application.core.offers.models.OfferEntity;
import com.fluxemail.application.core.offers.models.ResourceEntity;
import com.fluxemail.application.core.offers.models.SuppressionEntity;
import com.fluxemail.application.core.Networks.repositories.NetworkAccountRepository;
import com.fluxemail.application.core.offers.repositories.OfferRepository;
import com.fluxemail.application.core.offers.repositories.ResourceRepository;
import com.fluxemail.application.core.offers.repositories.SuppressionRepository;
import com.fluxemail.application.shared.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final ResourceRepository resourceRepository;
    private final SuppressionRepository suppressionRepository;
    private final NetworkAccountRepository networkAccountRepository;

    private final ModelMapper modelMapper;

    public List<OfferDto> getOffers(){

        var offers = offerRepository.findAll();
        var offerDtos = offers
                .stream()
                .map( offerEntity -> modelMapper.map( offerEntity , OfferDto.class ) )
                .collect(Collectors.toList());


        return offerDtos;
    }

    public OfferDto getOffer(Long offerId) {

        var offerEntity = offerRepository
                .findById(offerId)
                .orElseThrow(
                    () -> new ResourceNotFoundException("Offer id " + offerId +" not found")
                );

        var offerDto = modelMapper.map( offerEntity , OfferDto.class );

        return offerDto;
    }

    @Transactional
    public OfferDto createOffer(Long networkAccountId, OfferDto offer) {
        // convert from dto to entity
        var offerEntity = this.modelMapper.map( offer , OfferEntity.class);

        // find network by id
        var networkAccount = networkAccountRepository
                .findById( networkAccountId )
                .orElseThrow( () -> new ResourceNotFoundException("Network account id "+ networkAccountId +" not found !!") );

        offerEntity.setNetworkAccount(networkAccount);

        var createdOffer = this.offerRepository.save(offerEntity);

        var resources = offer.getResources()
                .stream()
                .map( resourceDto -> this.modelMapper.map( resourceDto , ResourceEntity.class) )
                .map( resourceEntity -> { resourceEntity.setOffer(createdOffer); return resourceEntity; })
                .collect(Collectors.toSet());
        // save resources
        resourceRepository.saveAll(resources);

        if( offer.getSuppression() != null ){
            var supprission = this.modelMapper.map( offer.getSuppression() , SuppressionEntity.class );
            supprission.setOffer(createdOffer);
            // save supprission
            var createdSupprission = suppressionRepository.save(supprission);
            // add to offer entity
            offerEntity.setSuppression(createdSupprission);
        }

        var createdOfferDto = this.modelMapper.map( createdOffer , OfferDto.class );

        return createdOfferDto;
    }
}
