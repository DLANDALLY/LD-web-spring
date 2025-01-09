package com.dly.safetynet.controllers;

import com.dly.safetynet.entities.Person;
import com.dly.safetynet.services.interfaces.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private IPerson person;

    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return person.findById(id).orElse(null);
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return person.findAll();
    }
}
