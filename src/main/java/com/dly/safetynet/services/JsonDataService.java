package com.dly.safetynet.services;

import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.JsonDataWrapper;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonDataService {
    private List<Object> listeners = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File filePath = new File("src/main/resources/data.json");
    private List<Person> persons;
    private List<FireStation> firestations;
    private List<MedicalRecord> medicalRecords;


    public void loadDataFromJson() {
        try {
            JsonDataWrapper dataWrapper = objectMapper.readValue(filePath, JsonDataWrapper.class);
            persons = dataWrapper.getPersons();
            firestations = dataWrapper.getFirestations();
            medicalRecords = dataWrapper.getMedicalrecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDataToJson(List<?> objects) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath, objects);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO probleme lorsque jaoute ou supprime des doonnées sur le fichier Json
//    public void writeData(DataStore dataStore) {
//        try {
//            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, dataStore);
//        } catch (IOException e) {
//            throw new RuntimeException("Erreur écriture JSON", e);
//        }
//    }
//
//    public void addPerson(Person newPerson) {
//        DataStore data = readData();
//        List<Person> persons = data.getPersons();
//        persons.add(newPerson);
//        writeData(data);
//    }

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
