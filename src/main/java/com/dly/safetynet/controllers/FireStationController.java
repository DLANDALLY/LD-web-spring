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

    /**
     * http://localhost:8080/firestation
     * Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete avec
     * HTTP :
     *         ● Ajout d'un mapping caserne/adresse
     *         ● Mettre à jour le numéro de la caserne de pompiers d'une adresse
     *         ● Supprimer le mapping d'une caserne ou d'une adresse
     */




     /**
      *
      * * http://localhost:8080/medicalRecord
     * Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete HTTP :
     *         ● Ajouter un dossier médical
     * ● Mettre à jour un dossier médical existant (comme évoqué précédemment,
     *                                              supposez que le prénom et le nom de famille ne changent pas)
     * ● Supprimer un dossier médical (utilisez une combinaison de prénom et de nom
     *         comme identificateur unique)
     */
}
