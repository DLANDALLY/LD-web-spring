package com.dly.safetynet.services;

import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.JsonDataWrapper;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class JsonDataService {
    private List<Object> listeners = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File filePath = new File("src/main/resources/data.json");
    @Getter
    private List<Person> persons;
    @Getter
    private List<FireStation> firestations;
    @Getter
    private List<MedicalRecord> medicalRecords;


    public void loadDataFromJson() {
        try {
            log.info("Loading data from json file {}", filePath);
            JsonDataWrapper dataWrapper = objectMapper.readValue(filePath, JsonDataWrapper.class);
            persons = dataWrapper.getPersons();
            firestations = dataWrapper.getFirestations();
            medicalRecords = dataWrapper.getMedicalrecords();
        } catch (IOException e) {
            log.error("Error while loading data from json file {}", filePath, e);
            e.printStackTrace();
        }
    }

    public void writeDataToJson(Object object) {
        try {
            log.info("Writing data to json file {}", filePath);
            JsonDataWrapper dataWrapper = objectMapper.readValue(filePath, JsonDataWrapper.class);

            var typeClass = object.getClass();
            if (typeClass.equals(Person.class)) dataWrapper.getPersons().add((Person) object);
            else if (typeClass.equals(FireStation.class)) dataWrapper.getFirestations().add((FireStation) object);
            else if (typeClass.equals(MedicalRecord.class)) dataWrapper.getMedicalrecords().add((MedicalRecord) object);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath, dataWrapper);
        }catch (IOException e) {
            log.error("Error while writing data to json file {}", filePath, e);
            e.getMessage();
        }
    }

    public void updateDataToJson(List<?> objects) {
        try {
            log.info("Updating data to json file {}", filePath);
            JsonDataWrapper dataWrapper = objectMapper.readValue(filePath, JsonDataWrapper.class);
            var typeClass = objects.getFirst().getClass();

            if (typeClass.equals(Person.class)) dataWrapper.setPersons((List<Person>) objects);
            else if (typeClass.equals(FireStation.class)) dataWrapper.setFirestations((List<FireStation>) objects);
            else if (typeClass.equals(MedicalRecord.class)) dataWrapper.setMedicalrecords((List<MedicalRecord>) objects);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath, dataWrapper);
        }catch (IOException e) {
            log.error("Error while updating data to json file {}", filePath, e);
            e.getMessage();
        }
    }

}
