package com.fluxemail.application.core.offers.services;

import com.fluxemail.application.core.offers.Dtos.CreativeDto;
import com.fluxemail.application.core.offers.models.CreativeEntity;
import com.fluxemail.application.core.offers.repositories.CreativeRepository;
import com.fluxemail.application.core.offers.repositories.OfferRepository;
import com.fluxemail.application.shared.exceptions.ResourceNotFoundException;
import com.fluxemail.application.web.offers.requests.CreativeRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreativeOfferService {

    private final CreativeRepository creativeRepository;
    private final OfferRepository offerRepository;
    private final FileService fileService;
    private final ModelMapper modelMapper;
    public List<CreativeDto> getAllCreatives(long offerId) {
        var offerCreatives = creativeRepository.findByOfferId(offerId);
        var offerCreativeDtos = offerCreatives
                .stream()
                .map( creativeEntity -> modelMapper.map( creativeEntity , CreativeDto.class ) )
                .collect(Collectors.toList());
        return  offerCreativeDtos;
    }

    public CreativeDto addCreative(
            long offerId,
            CreativeRequest creative
    ) {
        var offerImagePath = fileService.saveFile( offerId , "offer" , creative.getOfferImage() );
        var offerUnsubPath = fileService.saveFile( offerId , "offer" , creative.getUnsubscribeImage() );

        var offer = offerRepository
                .findActivatedById( offerId )
                .orElseThrow( () -> new ResourceNotFoundException("Offer id " + offerId + " not found "));

        var creativeEntity = CreativeEntity
                .builder()
                .offer(offer)
                .offerImage(offerImagePath)
                .unsubscribeImage(offerUnsubPath)
                .isActive(true)
                .build();

        var createdCreativeEntity = creativeRepository.save( creativeEntity );

        var createdCreativeDto = modelMapper.map( createdCreativeEntity , CreativeDto.class );
        return createdCreativeDto;
    }
}
