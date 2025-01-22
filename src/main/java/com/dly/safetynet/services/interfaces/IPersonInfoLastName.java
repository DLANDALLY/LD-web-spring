package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.personInfolastName.PersonInfoLastNameDto;

import java.util.List;

public interface IPersonInfoLastName {
    List<PersonInfoLastNameDto> getPersonInfoLastName(String lastName);
}
