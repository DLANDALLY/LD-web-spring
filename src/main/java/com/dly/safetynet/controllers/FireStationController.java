package com.dly.safetynet.controllers;

import com.dly.safetynet.services.interfaces.IFireStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/firestation")
public class FireStationController {
    @Autowired
    private IFireStation fireStationService;


    /**
     * http://localhost:8081/firestation?stationNumber=<station_number>
     * Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers
     * correspondante. Donc, si le numéro de station = 1, elle doit renvoyer les habitants
     * couverts par la station numéro 1. La liste doit inclure les informations spécifiques
     * suivantes : prénom, nom, adresse, numéro de téléphone.
     * De plus, elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants
     * (tout individu âgé de 18 ans ou moins) dans la zone desservie.
     */
    @GetMapping()
    public ResponseEntity<?> getFirestation(@RequestParam("stationNumber") String station){
        try {
            return ResponseEntity.ok(fireStationService.personCoverageByFireStation(station));
        } catch (IllegalArgumentException iae){
            return ResponseEntity.badRequest().body(iae.getMessage());
        }
    }







//
//     /**
//     * http://localhost:8080/flood/stations?stations=<a list of
//     *   station_numbers>
//     * Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper
//      * les personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants,
//      * et faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
//      */
//     /**
//     * http://localhost:8080/personInfolastName=<lastName>
//     * Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
//      * posologie et allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent toutes
//      * apparaître.
//      */
//     /**
//     * http://localhost:8080/communityEmail?city=<city>
//     * Cette url doit retourner les adresses mail de tous les habitants de la ville.
//     */
//    @GetMapping("/firestation/{stationNumber}=")
//    public void getFireStationById(@PathVariable() Long stationNumber){
//        fireStationRepository.findById(stationNumber);
//    }

}
