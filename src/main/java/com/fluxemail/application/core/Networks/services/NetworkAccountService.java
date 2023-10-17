package com.fluxemail.application.core.Networks.services;

import com.fluxemail.application.core.Networks.dtos.NetworkAccountDto;
import com.fluxemail.application.core.Networks.repositories.NetworkAccountRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NetworkAccountService {

    private final NetworkAccountRepository networkAccountRepository;
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
                .orElseThrow( () -> new RuntimeException("Network Account id " + networkAccountId + " not found "));
        var networkAccountDto = modelMapper.map(networkAccount , NetworkAccountDto.class);
         return networkAccountDto;
    }

    public NetworkAccountDto createNetworkAccount( NetworkAccountDto networkAccountDto ){
        return null;
    }

    public NetworkAccountDto updateNetworkAccount( NetworkAccountDto networkAccountDto){
        return null;
    }

    public void deleteNetworkAccount( Long networkAccountId ){

    }
}
