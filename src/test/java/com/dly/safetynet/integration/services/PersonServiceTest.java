package com.dly.safetynet.integration.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.form.FireStationForm;
import com.dly.safetynet.form.PersonForm;
import com.dly.safetynet.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

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

        PersonDto personDto = personDtos.stream().filter(e -> e.getFirstName().equals("Roger")).findFirst().get();

        assertEquals("Roger", personDto.getFirstName());
        assertEquals("841-874-6512", personDto.getPhone());
    }

    @Test
    void shouldFindPersonsByLastName() {
        List<Person> persons = personService.findPersonsByLastName("Boyd");

        assertEquals(6, persons.size());
    }

    @Test
    void shouldFindEmailByCity() {
        List<String> emails = personService.findEmailByCity("Culver");

        assertEquals("drk@email.com", emails.getFirst());
    }

    @Test
    void shouldCreatPerson(){
        List<Person> persons = personService.findAllPersons();
        int randomNumber = generateNumber();
        PersonForm personForm = new PersonForm();
        personForm.setFirstName("Jean");
        personForm.setLastName("Paul");
        personForm.setAddress("45 rue du bat moulin");
        personForm.setCity("Montreal");
        personForm.setZip("H1A");
        personForm.setPhone("841-874-"+ randomNumber);
        personForm.setEmail("Jean"+ randomNumber +"@email.com");

        personService.creatPerson(personForm);
        assertEquals(personForm.getFirstName(), persons.getLast().getFirstName());
        assertEquals(personForm.getLastName(), persons.getLast().getLastName());
        assertEquals(personForm.getPhone(), persons.getLast().getPhone());
        assertEquals(personForm.getEmail(), persons.getLast().getEmail());
    }

    @Test
    void shouldEmailNotConform(){
        List<Person> persons = personService.findAllPersons();
        int randomNumber = generateNumber();
        PersonForm personForm = new PersonForm();
        personForm.setFirstName("Jean");
        personForm.setLastName("Paul");
        personForm.setAddress("45 rue du bat moulin");
        personForm.setCity("Montreal");
        personForm.setZip("H1A");
        personForm.setPhone("841-874-"+ randomNumber);
        personForm.setEmail("Jean"+ randomNumber );

        personService.creatPerson(personForm);
        assertEquals(personForm.getFirstName(), persons.getLast().getFirstName());
        assertEquals(personForm.getLastName(), persons.getLast().getLastName());
        assertEquals(personForm.getPhone(), persons.getLast().getPhone());
        assertEquals(personForm.getEmail(), persons.getLast().getEmail());
    }

    @Test
    void shouldUpdatePerson() {
        List<Person> persons = personService.findAllPersons();
        int randomNumber = generateNumber();
        PersonForm personForm = new PersonForm();
        personForm.setFirstName(persons.getFirst().getFirstName());
        personForm.setLastName(persons.getFirst().getLastName());
        personForm.setAddress(randomNumber +" avenue du chateau");
        personForm.setCity("Montreal");
        personForm.setZip("H1A");
        personForm.setPhone(persons.getFirst().getPhone());
        personForm.setEmail(persons.getFirst().getEmail());

        personService.updatePerson(personForm);

        assertEquals(personForm.getAddress(), persons.getFirst().getAddress());
        assertEquals(personForm.getPhone(), persons.getFirst().getPhone());
        assertEquals(personForm.getEmail(), persons.getFirst().getEmail());

    }

    @Test
    void shouldDeletePerson() {
        List<Person> persons = personService.findAllPersons();
        Person person = new Person();
        person.setFirstName(persons.getLast().getFirstName());
        person.setLastName(persons.getLast().getLastName());
        person.setPhone(persons.getLast().getPhone());
        person.setEmail(persons.getLast().getEmail());

        assertEquals("The person has been successfully deleted",personService.deletePerson(person));
    }

    int generateNumber() {
        Random random = new Random();
        return random.nextInt(1000 - 1 + 1) + 1;
    }
}