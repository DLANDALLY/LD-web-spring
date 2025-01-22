package com.dly.safetynet.controllers;

import com.dly.safetynet.services.interfaces.IFire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fire")
public class FireController {
    @Autowired
    private IFire fireService;
    /**
     * http://localhost:8081/fire?address=<address>
     * Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi
     * que le numéro de la caserne de pompiers la desservant. La liste doit inclure le nom,
     * le numéro de téléphone, l'âge et les antécédents médicaux (médicaments, posologie et allergies)
     * de chaque personne.
     */
    @GetMapping()
    public ResponseEntity<?> getFireAddress(@RequestParam("address") String address){
        try {
            return ResponseEntity.ok(fireService.getFireAddress(address));
        }catch (IllegalArgumentException iae){
            return ResponseEntity.badRequest().body(iae.getMessage());
        }
    }

    //TODO Probleme avec le parametre phone = null
    //TODO Probleme de doublon avec houseHolds

}
