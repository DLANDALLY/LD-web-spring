package com.dly.safetynet.controllers;

import com.dly.safetynet.entities.Person;
import com.dly.safetynet.services.interfaces.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private IPerson personService;

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.findById(id).orElse(null);
    }

    @PostMapping("/persons")
    public void addPerson(Person person) {
        personService.addPerson(person);
    }

    @PutMapping("/person/{id}")
    public void updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        //Person person = personService.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
        personService.updatePerson(personDetails);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }


}
