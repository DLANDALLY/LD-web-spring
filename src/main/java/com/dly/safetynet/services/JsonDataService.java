package com.dly.safetynet.services;

import com.dly.safetynet.entities.JsonDataWrapper;
import com.dly.safetynet.repositories.FireStationRepository;
import com.dly.safetynet.repositories.MedicalRecordRepository;
import com.dly.safetynet.repositories.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonDataService {
    private List<Object> listeners = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath = "src/main/resources/data.json";
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private FireStationRepository firestationRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    /**
     * Load data from JSON file in repository entity
     */
    public void loadDataFromJson() {
        try {
            JsonDataWrapper dataWrapper = objectMapper.readValue(
                    new File(filePath), JsonDataWrapper.class);
            personRepository.saveAll(dataWrapper.getPersons());
            firestationRepository.saveAll(dataWrapper.getFirestations());
            medicalRecordRepository.saveAll(dataWrapper.getMedicalrecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Edit the specified patient record in the database and update the database
     * @param object
     * @param objects
     * @param <T>
     * @throws IOException
     */
    public <T> void edit(T object, List<T> objects) throws IOException {
        objects.removeIf(o -> o.getClass().equals(object));
        objects.add(object);
        objectMapper.writeValue(new File(filePath), objects);
    }

    public <T> void save(T object, List<T> objects) throws IOException {
        objects.add(object);
        objectMapper.writeValue(new File(filePath), objects);
    }

    public <T>void deleteByObject(T object, List<T> objects) {
        objects.removeIf(u -> u.getClass().equals(object));
    }
}
