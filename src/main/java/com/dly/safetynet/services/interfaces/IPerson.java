package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.Person;

import java.util.List;

public interface IPerson {
    List<Person> findAllPersons();

    List<PersonDto> findPersonsDtoByAddress(String address);

    List<Person> findPersonsByLastName(String lastName);

    List<String> findEmailByCity(String city);
}
