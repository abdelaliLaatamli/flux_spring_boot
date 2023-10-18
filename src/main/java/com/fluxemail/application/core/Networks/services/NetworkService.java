package com.fluxemail.application.core.Networks.services;

import com.fluxemail.application.core.Networks.dtos.NetworkDto;
import com.fluxemail.application.core.Networks.models.NetworkEntity;
import com.fluxemail.application.core.Networks.repositories.NetworkRepository;
import com.fluxemail.application.shared.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NetworkService {



    private final ModelMapper modelMapper;

    private final NetworkRepository networkRepository;

    public List<NetworkDto> getNetworks(){

        var networks = networkRepository.findAllActivated();

        var networkDtos = networks.stream()
                .map( network -> modelMapper.map( network , NetworkDto.class ) )
                .collect(Collectors.toList());

        return networkDtos;
    }

    public NetworkDto getNetwork(Long networkId) {

        var network = networkRepository
                .findActiveById(networkId)
                .orElseThrow( () -> new ResourceNotFoundException("Network id " + networkId + " Not found" ) );

        var networkDto = modelMapper.map( network , NetworkDto.class );

        return networkDto;
    }

    public NetworkDto createNetwork(NetworkDto networkDto) {
        var networkEntity = modelMapper.map( networkDto , NetworkEntity.class );
        NetworkEntity createdNetwork ;
        try {
            createdNetwork = networkRepository.save(networkEntity);
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Database error on insertion");
        }
        var createdNetworkDto = modelMapper.map( createdNetwork , NetworkDto.class );
        return createdNetworkDto;
    }

    public NetworkDto updateNetwork(NetworkDto networkDto, long networkId) {

        var network = networkRepository
                .findActiveById(networkId)
                .orElseThrow(() -> new ResourceNotFoundException("Network id " + networkId + " Not found" ));

        network.setName( networkDto.getName() );
        network.setNetworkUrl(networkDto.getNetworkUrl());
        network.setNetworkApiUrl( networkDto.getNetworkApiUrl() );
        network.setNetworkType(networkDto.getNetworkType());

        var updatedNetwork = networkRepository.save(network);

        var updatedNetworkdto = modelMapper.map( updatedNetwork , NetworkDto.class );

        return updatedNetworkdto ;
    }

    public void deleteNetwork(long networkId) {

        var network = networkRepository
                .findActiveById(networkId)
                .orElseThrow(() -> new ResourceNotFoundException("Network id " + networkId + " Not found" ));

        network.setIsActive(false);
        networkRepository.save(network);

    }
}
