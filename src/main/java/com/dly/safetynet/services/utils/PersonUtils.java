package com.dly.safetynet.services.utils;

import com.dly.safetynet.entities.Person;
import com.dly.safetynet.form.PersonForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

    public static Person findExactPerson(Person person, List<Person> allPersons) {
        return allPersons.stream()
                .filter(p ->
                        p.getFirstName().equals(person.getFirstName()) &&
                        p.getLastName().equals(person.getLastName()) &&
                        p.getPhone().equals(person.getPhone()) &&
                        p.getEmail().equals(person.getEmail()))
                .findFirst().orElse(null);
    }

    public static boolean checkEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
