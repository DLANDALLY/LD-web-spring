package com.dly.safetynet.controllers;

import com.dly.safetynet.services.interfaces.IFlood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flood")
public class FloodController {
    @Autowired
    private IFlood floodService;

    /**
     * http://localhost:8081/flood/stations?stations=<a list of station_numbers>
     * Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper
     * les personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants,
     * et faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
     */
    @GetMapping("/stations")
    public ResponseEntity<?> getFloodStations(@RequestParam List<String> stations){
        try {
            return ResponseEntity.ok(floodService.getFloodStations(stations));
        } catch (IllegalArgumentException iae){
            return ResponseEntity.badRequest().body(iae.getMessage());
        }
    }
}
