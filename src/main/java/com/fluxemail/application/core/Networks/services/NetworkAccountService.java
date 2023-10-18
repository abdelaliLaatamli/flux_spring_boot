package com.fluxemail.application.core.Networks.services;

import com.fluxemail.application.core.Networks.dtos.NetworkAccountDto;
import com.fluxemail.application.core.Networks.models.NetworkAccountEntity;
import com.fluxemail.application.core.Networks.repositories.NetworkAccountRepository;
import com.fluxemail.application.core.Networks.repositories.NetworkRepository;
import com.fluxemail.application.shared.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NetworkAccountService {

    private final NetworkAccountRepository networkAccountRepository;
    private final NetworkRepository networkRepository;
    private final ModelMapper modelMapper;

    public List<NetworkAccountDto> listAllNetworkAccount(){

        var listAllNetworkAccount = this.networkAccountRepository.findAll();
        var listAllNetworkAccountDtos = listAllNetworkAccount
                .stream()
                .map( networkAccount -> modelMapper.map(networkAccount , NetworkAccountDto.class) )
                .collect(Collectors.toList());

        return listAllNetworkAccountDtos;

    }

    public List<NetworkAccountDto> listAllNetworkAccountByNetwork(Long networkId){

        networkRepository
                .findActiveById( networkId )
                .orElseThrow( () -> new ResourceNotFoundException("Network id " + networkId + " not found !!" ) );

        var listNetworkAccountsByNetwork = networkAccountRepository.findAllByNetworkId(networkId);

        var listNetworkAccountsByNetworkDto = listNetworkAccountsByNetwork
                .stream()
                .map( networkEntity -> modelMapper.map( networkEntity , NetworkAccountDto.class ) )
                .collect(Collectors.toList());

        return listNetworkAccountsByNetworkDto;
    }

    public NetworkAccountDto networkAccount(Long networkAccountId){
         var networkAccount = networkAccountRepository
                .findActivatedById(networkAccountId)
                .orElseThrow( () -> new ResourceNotFoundException("Network Account id " + networkAccountId + " not found ") );
        var networkAccountDto = modelMapper.map(networkAccount , NetworkAccountDto.class);
         return networkAccountDto;
    }

    public NetworkAccountDto createNetworkAccount(
            Long networkId,
            NetworkAccountDto networkAccountDto
    ){
        var network = networkRepository
                .findActiveById(networkId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Network id "+ networkId +" not found ")
                );

        var networkAccountEntity = modelMapper.map( networkAccountDto , NetworkAccountEntity.class );
        networkAccountEntity.setNetwork( network );

        var createdNetworkAccountEntity = networkAccountRepository.save( networkAccountEntity );

        var createdNetworkAccountDto = modelMapper.map( createdNetworkAccountEntity , NetworkAccountDto.class );

        return createdNetworkAccountDto;
    }

    public NetworkAccountDto updateNetworkAccount( NetworkAccountDto networkAccountDto){
        return null;
    }

    public void deleteNetworkAccount( Long networkAccountId ){

    }
}
