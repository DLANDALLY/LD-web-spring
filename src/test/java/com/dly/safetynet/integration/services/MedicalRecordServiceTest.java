package com.dly.safetynet.integration.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.services.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicalRecordServiceTest {
    @Autowired
    private MedicalRecordService medicalRecordService;

    @Test
    void shouldFindAllMedicalRecords() {
        List<MedicalRecord> medicalRecords= medicalRecordService.findAllMedicalRecords();

        assertEquals("John", medicalRecords.getFirst().getFirstName());
        assertEquals("03/06/1984", medicalRecords.getFirst().getBirthdate());
    }

    @Test
    void shouldFindMedicalRecordByFirstNameAndLastName() {
        List<MedicalRecord> medicalRecords = medicalRecordService.findMedicalRecordByFirstNameAndLastName(getPersonsDto());

        assertEquals("John", medicalRecords.getFirst().getFirstName());
        assertEquals("03/06/1984", medicalRecords.getFirst().getBirthdate());
    }

    @Test
    void shouldFindMedicalRecordByLastName() {
        List<MedicalRecord> medicalRecords = medicalRecordService.findMedicalRecordByLastName(getPersons());

        assertEquals("John", medicalRecords.getFirst().getFirstName());
        assertEquals("03/06/1984", medicalRecords.getFirst().getBirthdate());
    }

    List<PersonDto> getPersonsDto(){
        return List.of(
                new PersonDto("John","Boyd", "1509 Culver St", "841-874-6512"),
                new PersonDto("Jacob", "Boyd", "1509 Culver St","841-874-6513"),
                new PersonDto("Tenley", "Boyd", "1509 Culver St", "841-874-6514"));
    }

    List<Person> getPersons(){
        return List.of(
                new Person("John", "Boyd","1509 Culver St", "Culver","97451", "841-874-6512", "jaboyd@email.com"),
                new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com"),
                new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com"));
    }
}