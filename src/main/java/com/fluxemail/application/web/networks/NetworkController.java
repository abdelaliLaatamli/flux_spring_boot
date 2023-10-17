package com.fluxemail.application.web.networks;

import com.fluxemail.application.core.Networks.dtos.NetworkDto;
import com.fluxemail.application.core.Networks.services.NetworkAccountService;
import com.fluxemail.application.core.Networks.services.NetworkService;
import com.fluxemail.application.web.networks.requests.NetworkRequest;
import com.fluxemail.application.web.networks.responses.NetworkAccountResponse;
import com.fluxemail.application.web.networks.responses.NetworkResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/networks")
@AllArgsConstructor
public class NetworkController {

    private final NetworkService networkService;
    private final NetworkAccountService networkAccountService;
    private final ModelMapper modelMapper;

    @GetMapping("")
    private ResponseEntity<Object> allNetworks(){

        var network = networkService.getNetworks();

        return ResponseEntity.ok(network);

    }

    @GetMapping("/{networkId}")
    private ResponseEntity<NetworkResponse> network(@PathVariable("networkId") Long networkId){
        var network = networkService.getNetwork( networkId );
        var networkResponse = modelMapper.map( network , NetworkResponse.class );
        return ResponseEntity.ok( networkResponse );
    }

    @GetMapping("/{networkId}/network-accounts")
    private ResponseEntity<List<NetworkAccountResponse>> networkAccountsByNetwork(@PathVariable("networkId") Long networkId){

        var networkAccountDtos = networkAccountService.listAllNetworkAccountByNetwork(networkId);

        var networkAccountResponses = networkAccountDtos
                .stream()
                .map(networkAccountDto -> modelMapper.map( networkAccountDto , NetworkAccountResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok( networkAccountResponses );
    }


    @PostMapping
    private ResponseEntity<NetworkResponse> createNetwork(@Valid @RequestBody NetworkRequest networkRequest){
        var networkDto = modelMapper.map(networkRequest , NetworkDto.class);
        var createdNetworkDto = networkService.createNetwork( networkDto );
        var createdNetworkResponse = modelMapper.map( createdNetworkDto , NetworkResponse.class );
        return new ResponseEntity<NetworkResponse>(createdNetworkResponse , HttpStatus.ACCEPTED);
    }

    @PutMapping("/{networkId}")
    private ResponseEntity<NetworkResponse> updateNetwork(
            @PathVariable("networkId") long networkId,
            @RequestBody NetworkRequest networkRequest
    ){
        var networkDto = modelMapper.map( networkRequest , NetworkDto.class );
        var updatedNetworkDto = networkService.updateNetwork( networkDto , networkId );
        var updatedNetworkResponse = modelMapper.map( updatedNetworkDto , NetworkResponse.class );
        return new ResponseEntity<>(updatedNetworkResponse , HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{networkId}")
    private ResponseEntity<HttpStatus> deleteNetwork(@PathVariable("networkId") long networkId){

        networkService.deleteNetwork( networkId );

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
