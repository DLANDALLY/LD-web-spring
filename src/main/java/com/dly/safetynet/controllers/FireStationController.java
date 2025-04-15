package com.dly.safetynet.controllers;

import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.form.FireStationForm;
import com.dly.safetynet.form.PersonForm;
import com.dly.safetynet.services.interfaces.IFireStation;
import com.dly.safetynet.validator.FireStationFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/firestation")
public class FireStationController {
    @Autowired
    private IFireStation fireStationService;
    @Autowired
    private FireStationFormValidator fireStationFormValidator;

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
     * http://localhost:8081/firestation
     * Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete avec
     * HTTP :
     *         ● Ajout d'un mapping caserne/adresse
     *         ● Mettre à jour le numéro de la caserne de pompiers d'une adresse
     *         ● Supprimer le mapping d'une caserne ou d'une adresse
     */
    @PostMapping
    public ResponseEntity<?> createFirestation(@RequestBody FireStationForm fireStationForm, BindingResult result) {
        fireStationFormValidator.validate(fireStationForm, result);
        if (result.hasErrors()) return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);

        try {
            fireStationService.creatFireStation(fireStationForm);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Great! Firestation has been successfully saved");
        }catch (IllegalArgumentException | IOException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateFirestation(@RequestBody FireStationForm fireStationForm, BindingResult result) {
        fireStationFormValidator.validate(fireStationForm, result);
        if (result.hasErrors()) return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);

        try {
            fireStationService.updateFireStation(fireStationForm);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body("The Firestation has been successfully updated");
        }catch (IllegalArgumentException | IOException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFirestation(@RequestBody FireStation fireStation) {
        try{
            return new ResponseEntity<>(fireStationService.deleteFireStation(fireStation), HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException | IOException e){
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
