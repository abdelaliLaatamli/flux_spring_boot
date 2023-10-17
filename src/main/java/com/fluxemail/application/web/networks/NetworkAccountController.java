package com.fluxemail.application.web.networks;

import com.fluxemail.application.web.networks.requests.NetworkAccountRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/network-accounts")
@AllArgsConstructor
public class NetworkAccountController {

    @GetMapping("/")
    private ResponseEntity<Object> allNetworkAccounts(){

        return ResponseEntity.ok(null);
    }

    @GetMapping("/{account-id}")
    private ResponseEntity<Object> networkAccount( @PathVariable("account-id") Long accountId ){

        return ResponseEntity.ok(null);
    }


    @PostMapping
    private ResponseEntity<Object> createNetworkAccount(
            @RequestBody NetworkAccountRequest networkAccountRequest
    ){

        return new ResponseEntity<>(null , HttpStatus.CREATED);
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
