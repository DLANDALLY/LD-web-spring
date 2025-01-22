package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.Person;

import java.util.List;

public interface IPerson {
    List<PersonDto> findPersonsDtoByAddress(String address);

    List<Person> findPersonsByLastName(String lastName);
}
