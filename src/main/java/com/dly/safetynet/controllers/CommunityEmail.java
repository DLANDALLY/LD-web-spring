package com.dly.safetynet.controllers;

import com.dly.safetynet.services.interfaces.ICommunityEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/communityEmail")
public class CommunityEmail {
    @Autowired
    private ICommunityEmail communityEmailService;
    /**
     * http://localhost:8081/communityEmail?city=<city>
     * Cette url doit retourner les adresses mail de tous les habitants de la ville.
     */
    @GetMapping
    public ResponseEntity<?> getCommunityEmailByCity(@RequestParam String city){
        try {
            return ResponseEntity.ok(communityEmailService.getCommunityEmailByCity(city));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Invalid city");
        }
    }

}
