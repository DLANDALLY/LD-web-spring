package com.dly.safetynet.integration.validator;

import com.dly.safetynet.form.MedicalRecordForm;
import com.dly.safetynet.validator.MedicalRecordFormValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicalRecordFormValidatorTest {
    @Autowired
    private MedicalRecordFormValidator validator = new MedicalRecordFormValidator();

    @Test
    void validateWithFieldError() {
        MedicalRecordForm form = new MedicalRecordForm();
        form.setFirstName("");
        form.setLastName(" ");
        form.setBirthdate(null);

        Errors errors = new BeanPropertyBindingResult(form, "medicalRecordForm");

        validator.validate(form, errors);

        assertTrue(errors.hasErrors());
        assertEquals(3, errors.getErrorCount());
        assertNotNull(errors.getFieldError("firstName"));
        assertNotNull(errors.getFieldError("lastName"));
        assertNotNull(errors.getFieldError("birthdate"));
    }
}