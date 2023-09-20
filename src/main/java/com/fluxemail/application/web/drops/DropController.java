package com.fluxemail.application.web.drops;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/drops")
public class DropController {


    @GetMapping
    private ResponseEntity<String> getDrops(){
        return ResponseEntity.ok("this drops route");
    }
}
