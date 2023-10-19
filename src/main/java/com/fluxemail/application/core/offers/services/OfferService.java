package com.fluxemail.application.core.offers.services;

import com.fluxemail.application.core.offers.Dtos.OfferDto;
import com.fluxemail.application.core.offers.models.OfferEntity;
import com.fluxemail.application.core.offers.models.OfferStatus;
import com.fluxemail.application.core.offers.models.ResourceEntity;
import com.fluxemail.application.core.offers.models.SuppressionEntity;
import com.fluxemail.application.core.Networks.repositories.NetworkAccountRepository;
import com.fluxemail.application.core.offers.repositories.OfferRepository;
import com.fluxemail.application.core.offers.repositories.ResourceRepository;
import com.fluxemail.application.core.offers.repositories.SuppressionRepository;
import com.fluxemail.application.shared.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

        var offers = offerRepository.findAllActivated();
        var offerDtos = offers
                .stream()
                .map( offerEntity -> modelMapper.map( offerEntity , OfferDto.class ) )
                .collect(Collectors.toList());


        return offerDtos;
    }

    public OfferDto getOffer(Long offerId) {

        var offerEntity = offerRepository
                .findActivatedById(offerId)
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
                .findActivatedById( networkAccountId )
                .orElseThrow( () -> new ResourceNotFoundException("Network account id "+ networkAccountId +" not found !!") );

        offerEntity.setNetworkAccount(networkAccount);

        var createdOffer = this.offerRepository.save(offerEntity);

        var resources = offer.getResources()
                .stream()
                .map( resourceDto -> this.modelMapper.map( resourceDto , ResourceEntity.class) )
                .peek(resourceEntity -> resourceEntity.setOffer(createdOffer))
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

    public OfferDto updateOffer(
            Long offerId ,
            Long networkAccountId,
            OfferDto offerDto
    ) {

        var offerFound = offerRepository
                .findActivatedById(offerId)
                .orElseThrow( () -> new ResourceNotFoundException(" Offer id " + offerId + " not Found !!") );

        offerFound.setSid(offerDto.getSid());
        offerFound.setCampaignId(offerDto.getCampaignId());
        offerFound.setName(offerDto.getName());
        offerFound.setDescription(offerDto.getDescription());
        offerFound.setType(offerDto.getType());
        offerFound.setAmount(offerDto.getAmount());
        offerFound.setOfferUrl(offerDto.getOfferUrl());
        offerFound.setUnsubscribeUrl(offerDto.getUnsubscribeUrl());
        offerFound.setCountries(offerDto.getCountries());

        if( networkAccountId != null ){
            var networkAccount = networkAccountRepository
                    .findActivatedById(networkAccountId)
                    .orElseThrow( () -> new ResourceNotFoundException("Network Account id " +networkAccountId+ " Not found !!"));
            offerFound.setNetworkAccount(networkAccount);
        }

        var updatedOfferEntity = offerRepository.save(offerFound);

        var updatedOfferDto = modelMapper.map( updatedOfferEntity , OfferDto.class );

        return updatedOfferDto ;
    }

    public void deactiveteOffer(Long offerId) {

        var offerFound = offerRepository
                .findActivatedById(offerId)
                .orElseThrow( () -> new ResourceNotFoundException(" Offer id " + offerId + " not Found !!") );

        offerFound.setStatus(OfferStatus.INACTIVE);

        offerRepository.save(offerFound);

    }
}
