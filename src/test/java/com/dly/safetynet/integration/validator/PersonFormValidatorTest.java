package com.dly.safetynet.integration.validator;

import com.dly.safetynet.form.MedicalRecordForm;
import com.dly.safetynet.form.PersonForm;
import com.dly.safetynet.validator.PersonFormValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonFormValidatorTest {
@Autowired
private PersonFormValidator validator;

    @Test
    void validateWithFieldError() {
        PersonForm form = new PersonForm();
        form.setFirstName("");
        form.setLastName(" ");
        form.setAddress(" ");
        form.setCity("");
        form.setZip("");
        form.setPhone("");
        form.setEmail("");

        Errors errors = new BeanPropertyBindingResult(form, "personForm");

        validator.validate(form, errors);

        assertTrue(errors.hasErrors());
        assertEquals(7, errors.getErrorCount());
        assertNotNull(errors.getFieldError("firstName"));
        assertNotNull(errors.getFieldError("lastName"));
        assertNotNull(errors.getFieldError("address"));
    }
}