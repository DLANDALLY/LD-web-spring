package com.dly.safetynet.integration.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @Test
    void shouldFindAllPersons() {
        List<Person> persons = personService.findAllPersons();

        assertEquals("Jacob", persons.get(1).getFirstName());
        assertEquals("1509 Culver St", persons.get(1).getAddress());
        assertEquals("841-874-6513", persons.get(1).getPhone());
    }

    @Test
    void shouldFindPersonsDtoByAddress() {
        List<PersonDto> personDtos = personService.findPersonsDtoByAddress("1509 Culver St");

        assertEquals("Tenley", personDtos.get(2).getFirstName());
        assertEquals("841-874-6512", personDtos.get(2).getPhone());
    }

    @Test
    void shouldFindPersonsByLastName() {
        List<Person> persons = personService.findPersonsByLastName("Boyd");

        assertEquals(6, persons.size());
    }

    @Test
    void shouldFindEmailByCity() {
        List<String> emails = personService.findEmailByCity("Culver");

        assertEquals("jaboyd@email.com", emails.getFirst());
    }
}