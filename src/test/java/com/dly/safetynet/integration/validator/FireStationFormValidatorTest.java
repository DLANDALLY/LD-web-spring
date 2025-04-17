package com.dly.safetynet.integration.validator;

import com.dly.safetynet.form.FireStationForm;
import com.dly.safetynet.form.MedicalRecordForm;
import com.dly.safetynet.validator.FireStationFormValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FireStationFormValidatorTest {
    @Autowired
    private FireStationFormValidator validator = new FireStationFormValidator();

    @Test
    void validateWithFieldError() {
        FireStationForm form = new FireStationForm();
        form.setStation("");
        form.setAddress(" ");

        Errors errors = new BeanPropertyBindingResult(form, "fireStationForm");

        validator.validate(form, errors);

        assertTrue(errors.hasErrors());
        assertEquals(2, errors.getErrorCount());
        assertNotNull(errors.getFieldError("station"));
        assertNotNull(errors.getFieldError("address"));
    }
}