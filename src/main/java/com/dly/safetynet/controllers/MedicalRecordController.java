package com.dly.safetynet.controllers;

import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.form.MedicalRecordForm;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.validator.MedicalRecordFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {
    @Autowired
    private IMedicalRecord medicalRecordService;
    @Autowired
    private MedicalRecordFormValidator medicalRecordFormValidator;

    /**
     * http://localhost:8081/medicalRecord
     * Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete HTTP :
     *  ● Ajouter un dossier médical
     *  ● Mettre à jour un dossier médical existant (comme évoqué précédemment,
     *      supposez que le prénom et le nom de famille ne changent pas)
     *  ● Supprimer un dossier médical (utilisez une combinaison de prénom et de nom comme identificateur unique)
     */
    @PostMapping
    public ResponseEntity<?> createMedicalRecord(@RequestBody MedicalRecordForm medicalRecordForm, BindingResult result) {
        medicalRecordFormValidator.validate(medicalRecordForm, result);
        if (result.hasErrors()) return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);

        try {
            medicalRecordService.creatMedicalRecord(medicalRecordForm);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Great! The medical record of "+ medicalRecordForm.getFirstName() +" has been successfully saved");
        }catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateMedicalRecord(@RequestBody MedicalRecordForm medicalRecordForm, BindingResult result) {
        medicalRecordFormValidator.validate(medicalRecordForm, result);
        if (result.hasErrors()) return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);

        try {
            medicalRecordService.updateMedicalRecord(medicalRecordForm);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body("The medical record data of " + medicalRecordForm.getFirstName() + " has been successfully updated");
        }catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        try{
            return new ResponseEntity<>(medicalRecordService.deleteMedicalRecord(medicalRecord), HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException e){
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
