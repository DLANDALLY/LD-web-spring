package com.dly.safetynet.services.utils;

import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;

import java.util.List;

public class PersonUtils {

    /**
     * Checks whether a Person instance exists in the provided list
     * @param person the person to look for
     * @param allPersons the list of persons to search in
     * @return true if the list contains the specified person
     */
    public static boolean checkPersonExists(Person person, List<Person> allPersons) {
          return allPersons.stream()
                .anyMatch(p ->
                        p.getEmail().equals(person.getEmail()) ||
                        p.getPhone().equals(person.getPhone()));
    }

    public static boolean samePerson(Person person, List<Person> allPersons) {
        return allPersons.stream()
                .anyMatch(p ->
                        p.getFirstName().equals(person.getFirstName()) &&
                        p.getLastName().equals(person.getLastName()) &&
                        p.getAddress().equals(person.getAddress()) &&
                        p.getCity().equals(person.getCity()) &&
                        p.getZip().equals(person.getZip())&&
                        p.getPhone().equals(person.getPhone()) &&
                        p.getEmail().equals(person.getEmail()));
    }

    public static Person findExactPerson(Person person, List<Person> allPersons) {
        return allPersons.stream()
                .filter(p ->
                        p.getFirstName().equals(person.getFirstName()) &&
                        p.getLastName().equals(person.getLastName()) &&
                        p.getPhone().equals(person.getPhone()) &&
                        p.getEmail().equals(person.getEmail()))
                .findFirst().orElse(null);
    }

    public static boolean checkPersonExists(MedicalRecord medicalRecord, List<Person> allPersons) {
        return allPersons.stream()
                .anyMatch(p ->
                        p.getFirstName().equals(medicalRecord.getFirstName()) &&
                        p.getLastName().equals(medicalRecord.getLastName()));
    }
}
