package com.dly.safetynet.controllers;

import com.dly.safetynet.entities.Person;
import com.dly.safetynet.form.PersonForm;
import com.dly.safetynet.services.interfaces.IPerson;
import com.dly.safetynet.validator.PersonFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private IPerson personService;
    @Autowired
    private PersonFormValidator personFormValidator;

    /**
     * http://localhost:8081/person
     * Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete avec
     * HTTP :
     *      ● Ajouter une nouvelle personne
     *      ● Mettre à jour une personne existante (pour le moment, supposons que le
     *          prénom et le nom de famille ne changent pas, mais que les autres champs
     *          peuvent être modifiés)
     *      ● Supprimer une personne (utilisez une combinaison de prénom et de nom
     *          comme identificateur unique)
     */
    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody PersonForm personForm, BindingResult result) {
        personFormValidator.validate(personForm, result);
        if (result.hasErrors()) return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);

        try {
            personService.creatPerson(personForm);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Great! "+ personForm.getFirstName() +" has been successfully saved");
        }catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePerson(@RequestBody PersonForm personForm, BindingResult result) {
        personFormValidator.validate(personForm, result);
        if (result.hasErrors()) return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);

        try {
            personService.updatePerson(personForm);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body("The personal data of " + personForm.getFirstName() + " has been successfully updated");
        }catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePerson(@RequestBody Person person) {
        try{
            return new ResponseEntity<>(personService.deletePerson(person), HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException e){
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
