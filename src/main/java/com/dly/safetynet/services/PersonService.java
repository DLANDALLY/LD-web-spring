package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.form.PersonForm;
import com.dly.safetynet.services.interfaces.IPerson;
import com.dly.safetynet.services.utils.PersonUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService implements IPerson {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JsonDataService jsonData;

    @Override
    public List<Person> findAllPersons() {
        long idCounter = 1;
        List<Person> persons = jsonData.getPersons();
        for (Person person : persons) {
            person.setId(idCounter++);
        }
        return persons;
    }

    @Override
    public List<PersonDto> findPersonsDtoByAddress(String address) {
        return findAllPersons()
                .stream()
                .filter(p -> p.getAddress().equals(address))
                .map(p -> modelMapper.map(p, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findPersonsByLastName(String lastName) {
        return findAllPersons()
                .stream()
                .filter(p -> p.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findEmailByCity(String city) {
        return findAllPersons().stream()
                .filter(p -> p.getCity().equals(city))
                .map(person -> person.getEmail())
                .collect(Collectors.toList());
    }

    @Override
    public void creatPerson(PersonForm personForm) throws IOException {
        List<Person> persons = findAllPersons();
        Person person = personMapper(personForm);
        boolean personExist = PersonUtils.checkPersonExists(person, persons);
        if (personExist) throw new IllegalArgumentException("Person already exists");

        persons.add(person);
        jsonData.writeDataToJson(person);
    }

    @Override
    public void updatePerson(PersonForm personForm) throws IOException {
        List<Person> persons = findAllPersons();
        Person person = personMapper(personForm);

        Person foundPerson = PersonUtils.findExactPerson(person, persons);
        boolean samePerson = PersonUtils.samePerson(person, persons);

        if (foundPerson == null) throw new IllegalArgumentException("Person not found");
        if (samePerson) throw new IllegalArgumentException("Oops, no changes received !");

        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).equals(foundPerson))
                persons.set(i, person);
        }
        jsonData.updateDataToJson(persons);
    }

    @Override
    public String deletePerson(Person person) throws IOException {
        List<Person> persons = findAllPersons();
        Person foundPerson = PersonUtils.findExactPerson(person, persons);
        if (foundPerson == null) throw new IllegalArgumentException("Person not found");

        persons.removeIf(p -> p.equals(foundPerson));
        jsonData.updateDataToJson(persons);
        return "The person has been successfully deleted";
    }

    private Person personMapper(PersonForm personForm) {
        return  modelMapper.map(personForm, Person.class);
    }
}
