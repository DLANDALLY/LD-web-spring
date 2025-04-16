package com.dly.safetynet.integration.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.form.FireStationForm;
import com.dly.safetynet.form.MedicalRecordForm;
import com.dly.safetynet.services.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @Test
    void shouldCreatMedicalRecord(){
        List<MedicalRecord> fireStations = medicalRecordService.findAllMedicalRecords();
        int number = generateNumber();

        MedicalRecordForm medicalRecordForm = new MedicalRecordForm();
        medicalRecordForm.setFirstName("John");
        medicalRecordForm.setLastName("Boyd");
        medicalRecordForm.setBirthdate(number +"/06/19"+ number);
        String birthdate = medicalRecordForm.getBirthdate();

        medicalRecordService.creatMedicalRecord(medicalRecordForm);

        assertEquals("John", fireStations.getFirst().getFirstName());
        assertEquals("Boyd", fireStations.getFirst().getLastName());
        assertEquals(birthdate, fireStations.getLast().getBirthdate());
    }

    @Test
    void shouldCantCreatMedicalRecordBefortAPerson(){
        List<MedicalRecord> fireStations = medicalRecordService.findAllMedicalRecords();
        int number = generateNumber();
        MedicalRecordForm medicalRecordForm = new MedicalRecordForm();
        medicalRecordForm.setFirstName("Alpha");
        medicalRecordForm.setLastName("Delta");
        medicalRecordForm.setBirthdate(number +"/06/19"+ number);

        assertThrowsExactly(IllegalArgumentException.class, () -> medicalRecordService.creatMedicalRecord(medicalRecordForm));
    }

    @Test
    void shouldUpdateMedicalRecord() {
        List<MedicalRecord> fireStations = medicalRecordService.findAllMedicalRecords();
        MedicalRecordForm medicalRecordForm = new MedicalRecordForm();
        medicalRecordForm.setFirstName(fireStations.getFirst().getFirstName());
        medicalRecordForm.setLastName(fireStations.getFirst().getLastName());
        medicalRecordForm.setBirthdate(fireStations.getFirst().getBirthdate());
        medicalRecordForm.setAllergies(generateList("Allergies"));
        medicalRecordForm.setMedications(generateList("Medications"));

        int number = medicalRecordForm.getMedications().size();
        int number2 = medicalRecordForm.getAllergies().size();

        medicalRecordService.updateMedicalRecord(medicalRecordForm);
        assertEquals(number, fireStations.getFirst().getMedications().size());
        assertEquals(number2, fireStations.getFirst().getAllergies().size());
    }

    @Test
    void shouldDeleteMedicalRecord() {
        List<MedicalRecord> medicalRecords = medicalRecordService.findAllMedicalRecords();
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(medicalRecords.getLast().getFirstName());
        medicalRecord.setLastName(medicalRecords.getLast().getLastName());
        medicalRecord.setBirthdate(medicalRecords.getLast().getBirthdate());

        assertEquals("The medical record has been successfully deleted",medicalRecordService.deleteMedicalRecord(medicalRecord));
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

    int generateNumber() {
        Random random = new Random();
        return random.nextInt(100 - 1 + 1) + 1;
    }

    List<String> generateList(String objet) {
        int number = generateNumber();
        List<String> list = new ArrayList<>();

        for (int i = 0; i <= number; i++) {
            list.add(objet);
        }
        System.out.println("Generated number: " + number);

        return list;
    }
}