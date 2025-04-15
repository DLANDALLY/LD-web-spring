package com.dly.safetynet.validator;

import com.dly.safetynet.form.FireStationForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FireStationFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return FireStationForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FireStationForm fireStationForm = (FireStationForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.fireStationForm.address", "Address is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "station", "NotEmpty.fireStationForm.station", "Station is required");
    }
}
