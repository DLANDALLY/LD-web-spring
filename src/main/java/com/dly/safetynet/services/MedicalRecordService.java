package com.dly.safetynet.services;

import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.repositories.MedicalRecordRepository;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecord {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public List<MedicalRecord> findBirthdayByFirstNameAndLastName(List<Person> persons) {
        List<MedicalRecord> birthdays = new ArrayList<>();
        persons.forEach(person -> birthdays.add(medicalRecordRepository
                .findBirthdayByFirstnameAndLastname(person.getFirstName(), person.getLastName())));
        return birthdays;
    }

    @Override
    public MedicalRecord findBirthdayByFirstNameAndLastName(String firstName, String lastName) {
        return medicalRecordRepository.findBirthdayByFirstnameAndLastname(firstName, lastName);
    }
}
