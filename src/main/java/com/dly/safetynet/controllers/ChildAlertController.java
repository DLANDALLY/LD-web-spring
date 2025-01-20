package com.dly.safetynet.controllers;

import com.dly.safetynet.services.interfaces.IChildAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/childAlert")
public class ChildAlertController {
    @Autowired
    private IChildAlert childAlertService;

    /**
     * http://localhost:8081/childAlert?address=<address>
     * Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
     * La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
     * membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.
     */
    @GetMapping()
    public ResponseEntity<?> getChildAlert(@RequestParam("address")String address){
        try {
            return ResponseEntity.ok(childAlertService.getChildAlert(address).getChildren());
        }catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(iae.getMessage());
        }
    }
}
