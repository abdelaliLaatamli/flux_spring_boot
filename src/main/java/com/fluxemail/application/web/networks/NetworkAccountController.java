package com.fluxemail.application.web.networks;

import com.fluxemail.application.core.Networks.dtos.NetworkAccountDto;
import com.fluxemail.application.core.Networks.services.NetworkAccountService;
import com.fluxemail.application.web.networks.requests.NetworkAccountRequest;
import com.fluxemail.application.web.networks.responses.NetworkAccountResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/network-accounts")
@AllArgsConstructor
public class NetworkAccountController {

    private final NetworkAccountService networkAccountService;
    private final ModelMapper modelMapper;

    @GetMapping("")
    private ResponseEntity<List<NetworkAccountResponse>> allNetworkAccounts(){

        var listAllNetworkAccount = this.networkAccountService.listAllNetworkAccount();
        var listNetowrkAccountReponse = listAllNetworkAccount
                .stream()
                .map( networkAccountDto -> modelMapper.map(networkAccountDto , NetworkAccountResponse.class) )
                .collect(Collectors.toList());

        return ResponseEntity.ok(listNetowrkAccountReponse);
    }

    @GetMapping("/{account-id}")
    private ResponseEntity<NetworkAccountResponse> networkAccount( @PathVariable("account-id") Long accountId ){

        var networkAccount = networkAccountService.networkAccount( accountId );
        var networkAccountResponse = modelMapper.map( networkAccount , NetworkAccountResponse.class );

        return ResponseEntity.ok(networkAccountResponse );
    }


    @PostMapping
    private ResponseEntity<NetworkAccountResponse> createNetworkAccount(
            @RequestBody NetworkAccountRequest networkAccountRequest
    ){
        var networkAccountDto = modelMapper.map( networkAccountRequest , NetworkAccountDto.class );
        networkAccountDto.setId(null);
        var createdNetworkAccountDto = networkAccountService.createNetworkAccount( networkAccountRequest.getNetworkId() , networkAccountDto );
        var createdNetworkAccountResponse = modelMapper.map( createdNetworkAccountDto , NetworkAccountResponse.class );
        return new ResponseEntity<>(createdNetworkAccountResponse , HttpStatus.CREATED);
    }

    @PutMapping("/{account-id}")
    private ResponseEntity<Object> updateNetworkAccount(
            @PathVariable("account-id") Long accountId ,
            @RequestBody NetworkAccountRequest networkAccountRequest
            ){

        return new ResponseEntity<>(null , HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{account-id}")
    private ResponseEntity<Object> deleteNetworkAccount( @PathVariable("account-id") Long accountId ){

        return new ResponseEntity<>(null , HttpStatus.NO_CONTENT);
    }

}
