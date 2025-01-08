package com.dly.safetynet.repositories;

import com.dly.safetynet.entities.Person;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface PersonRepository extends JpaRepository<Person,Long> {
}
