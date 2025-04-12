package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.form.PersonForm;

import java.io.IOException;
import java.util.List;

public interface IPerson {
    List<Person> findAllPersons();

    List<PersonDto> findPersonsDtoByAddress(String address);

    List<Person> findPersonsByLastName(String lastName);

    List<String> findEmailByCity(String city);

    void creatPerson(PersonForm personForm) throws IOException;

    void updatePerson(PersonForm personForm) throws IOException;

    String deletePerson(Person person) throws IOException;
}
