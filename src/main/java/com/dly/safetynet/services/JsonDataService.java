package com.dly.safetynet.services;

import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.JsonDataWrapper;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonDataService {
    private List<Object> listeners = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath = "src/main/resources/data.json";
    private List<Person> persons;
    private List<FireStation> firestations;
    private List<MedicalRecord> medicalRecords;

    /**
     * Load data from JSON file in repository entity
     */
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
