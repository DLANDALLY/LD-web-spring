package com.dly.safetynet.controllers;

import com.dly.safetynet.services.interfaces.IPersonInfoLastName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PersonInfoLastNameController {
    @Autowired
    private IPersonInfoLastName personInfoService;

    /**
     * http://localhost:8081/personInfolastName=<lastName>
     * Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
     * posologie et allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent toutes
     * apparaître.
     */
    @GetMapping
    public ResponseEntity<?> getPersonInfoLastName(@RequestParam("personInfolastName") String personInfolastName){
        try {
            return ResponseEntity.ok(personInfoService.getPersonInfoLastName(personInfolastName));
        } catch (IllegalArgumentException iae){
            return ResponseEntity.badRequest().body(iae.getMessage());
        }
    }
}
