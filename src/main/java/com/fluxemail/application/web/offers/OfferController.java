package com.fluxemail.application.web.offers;

import com.fluxemail.utils.ssh.jsch.JSchExampleSSHConnection;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/offers")
@AllArgsConstructor
public class OfferController {

    @GetMapping
    private ResponseEntity<String> getOffers(){
//        JSchExampleSSHConnection.test_ssh();
//        JSchExampleSSHConnection.test_ssh_key_string();
//        JSchExampleSSHConnection.test_ssk_key_file();
//        JSchExampleSSHConnection.ssh_out_class();
//        JSchExampleSSHConnection.ssh_key_string_class();
        JSchExampleSSHConnection.ssh_key_private_class();
        return ResponseEntity.ok("list all offers");
    }

    @GetMapping("/{offerId}")
    private ResponseEntity<String> getOffer(@PathVariable(value = "offerId") Long offerId){
        return ResponseEntity.ok(" Get Offer details " + offerId);
    }

    @PutMapping("/{offerId}")
    private ResponseEntity<String> putOffer(@PathVariable(value = "offerId") Long offerId){
        return ResponseEntity.ok(" Put Offer details " + offerId);
    }

    @DeleteMapping("/{offerId}")
    private ResponseEntity<Void> deleteOffer(@PathVariable(value = "offerId") Long offerId){
        return ResponseEntity.noContent().build();
    }

}
