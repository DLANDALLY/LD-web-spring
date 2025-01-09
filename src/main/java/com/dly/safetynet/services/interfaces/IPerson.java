package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.entities.Person;

import java.util.List;
import java.util.Optional;

public interface IPerson {
    List<Person> findAll();

    Optional<Person> findById(Long id);
}
