package com.dly.safetynet.validator;

import com.dly.safetynet.form.MedicalRecordForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class MedicalRecordFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MedicalRecordForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MedicalRecordForm medicalRecordForm = (MedicalRecordForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.medicalRecordForm.firstName", "First name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.medicalRecordForm.lastName", "Last name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthdate", "NotEmpty.medicalRecordForm.birthdate", "Birthdate is required");
    }
}
