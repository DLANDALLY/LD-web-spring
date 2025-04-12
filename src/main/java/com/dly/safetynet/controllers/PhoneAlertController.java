package com.dly.safetynet.controllers;

import com.dly.safetynet.services.interfaces.IPhoneAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {
    @Autowired
    private IPhoneAlert phoneAlertService;

    /**
     * http://localhost:8081/phoneAlert?firestation=<firestation_number>
     * Cette url doit retourner une liste des numéros de téléphone des résidents desservis
     * par la caserne de pompiers.Nous l'utiliserons pour envoyer des messages texte d'urgence
     * à des foyers spécifiques.
     */
    @GetMapping()
    public ResponseEntity<?> getPhoneAlert(@RequestParam("firestation") int firestationNumber){
        try {
            return ResponseEntity.ok(phoneAlertService.getPhoneAlert(firestationNumber));
        } catch (IllegalArgumentException iae){
            return ResponseEntity.badRequest().body(iae.getMessage());
        }
    }
}