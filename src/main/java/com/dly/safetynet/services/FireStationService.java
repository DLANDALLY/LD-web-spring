package com.dly.safetynet.services;

import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.repositories.FireStationRepository;
import com.dly.safetynet.services.interfaces.IFireStation;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class FireStationService implements IFireStation {
    @Autowired
    private IPerson personService;
    @Autowired
    private FireStationRepository fireStationRepository;
    @Autowired
    private IMedicalRecord recordService;


    @Override
    public Map<FireStation, List<Person>> personCoverageByFireStation(String station) {
        //Find by numero de station
        List<FireStation> fireStations = getFireStationByStation(station);

        //List de person by address
        //DATA person : prénom, nom, adresse, numéro de téléphone
        Map<FireStation, List<Person>> coverageData = new HashMap<>();
        for (FireStation station1 : fireStations) {
            coverageData.put(station1, personService.findByAddress(station1.getAddress()));
        }

        coverageData.forEach((k, v) -> System.out.println("## FireStation : " + k.getAddress() + " - " + v.size() + " person(s)"));


        //Count adults and children by age 18 and under
        //List<MedicalRecord> birthdays = recordService.findBirthdayByFirstNameAndLastName(persons);
        //String countPerson = countPerson(birthdays);

        return coverageData;
    }

    private void modelAsup(Map<FireStation, List<Person>> persons){
        persons.forEach((k, v) -> {
            List<MedicalRecord> birthdays = recordService.findBirthdayByFirstNameAndLastName(v);
            String countPerson = countPerson(birthdays);
            System.out.println("## FireStation : " + k.getAddress() + " - " + countPerson);
        });
    }

    @Override
    public List<FireStation> getFireStationByStation(String station){
        return fireStationRepository.findByStation(station);
    }

    private boolean isOver18(String birthDate) {
        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateOfBirth = LocalDate.parse(birthDate, formatter);

        Period age = Period.between(dateOfBirth, today);
        return age.getYears() >= 18;
    }

    private String countPerson(List<MedicalRecord> medicalRecords) {
        int adultCount = 0;
        int childCount = 0;
        Date now = new Date();
        for (MedicalRecord medicalRecord : medicalRecords){
            if (isOver18(medicalRecord.getBirthdate())) adultCount++;
            else childCount++;
        }
        System.out.println("Child count: " + childCount + " adult count: " + adultCount);
        return "Child count: " + childCount + " adult count: " + adultCount;
    }
}
