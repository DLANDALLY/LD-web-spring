package com.dly.safetynet.integration.config;

import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.JsonDataWrapper;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonDatabaseTest {
    private List<Object> listeners = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath = "src/test/resources/dataTest.json";
    private List<Person> persons;
    private List<FireStation> firestations;
    private List<MedicalRecord> medicalRecords;


    public void loadDataFromJson() {
        try {
            JsonDataWrapper dataWrapper = objectMapper.readValue(
                    new File(filePath), JsonDataWrapper.class);
            persons = dataWrapper.getPersons();
            firestations = dataWrapper.getFirestations();
            medicalRecords = dataWrapper.getMedicalrecords();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<FireStation> getFirestations() {
        return firestations;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }
}
