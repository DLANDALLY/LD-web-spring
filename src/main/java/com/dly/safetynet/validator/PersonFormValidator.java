package com.dly.safetynet.validator;

import com.dly.safetynet.form.PersonForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.dly.safetynet.services.utils.PersonUtils.checkEmail;

@Component
public class PersonFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PersonForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonForm personForm = (PersonForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.personForm.firstName", "First name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.personForm.lastName", "Last name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.personForm.address", "Address is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty.personForm.city", "City is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "NotEmpty.personForm.zip", "Zip is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.personForm.phone", "Phone is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.personForm.email", "Email is required");

        if(!checkEmail(personForm.getEmail()) || personForm.getEmail() == null){
            errors.rejectValue("email", "email.invalid", "L'adresse mail n'est pas valide");
        }
    }


}
