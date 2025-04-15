package com.dly.safetynet.services;

import com.dly.safetynet.dto.FireStationResponse;
import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.form.FireStationForm;
import com.dly.safetynet.form.PersonForm;
import com.dly.safetynet.services.interfaces.IFireStation;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import com.dly.safetynet.services.utils.FireStationUtils;
import com.dly.safetynet.services.utils.PersonUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FireStationService implements IFireStation {
    @Autowired
    private IPerson personService;
    @Autowired
    private IMedicalRecord medicalRecordService;
    @Autowired
    private JsonDataService jsonData;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<FireStation> findAllFireStations() {
        return jsonData.getFirestations();
    }

    @Override
    public FireStationResponse personCoverageByFireStation(String station) {
        if (station == null || station.isBlank()) throw new IllegalArgumentException("Station number cannot be null or blank");

        List<String> address = findAddress(station);
        if (address.isEmpty()) throw new IllegalArgumentException("No address is covered by this station number");

        List<PersonDto> persons = getPersonsDto(address);
        if (persons.isEmpty()) throw new IllegalArgumentException("No residents are covered by this station number");

        List<MedicalRecord> birthdays = getBirthdays(persons);
        if (birthdays.isEmpty()) throw new IllegalArgumentException("The dates of birth are not entered correctly. ");

        int adultCount = getAdultCount(birthdays);
        int childCount = birthdays.size() - adultCount;

        return new FireStationResponse(station, persons, adultCount, childCount);
    }

    @Override
    public List<String> findAddress(String station) {
        return findAllFireStations().stream()
               .filter(fs -> fs.getStation().equals(station))
               .map(FireStation::getAddress)
               .collect(Collectors.toList());
    }

    @Override
    public FireStation findFireStationByAddress(String address){
        return findAllFireStations()
                .stream()
                .filter(fs -> fs.getAddress().equals(address))
                .findFirst()
                .get();
    }

    @Override
    public List<PersonDto> getPersonsDto(List<String> addresses){
        return addresses.stream()
                .flatMap(address -> personService.findPersonsDtoByAddress(address).stream())
                .toList();
    }

    @Override
    public List<PersonDto> getPersonsDto(Set<String> addresses){
        return addresses.stream()
                .flatMap(address -> personService.findPersonsDtoByAddress(address).stream())
                .toList();
    }

    @Override
    public List<MedicalRecord> getBirthdays(List<PersonDto> persons){
        return medicalRecordService.findMedicalRecordByFirstNameAndLastName(persons);
    }

    @Override
    public int getAdultCount(List<MedicalRecord> medicalRecords){
        return (int) medicalRecords.stream()
                .filter(m -> isOver18(m.getBirthdate()))
                .count();
    }

    @Override
    public boolean isOver18(String birthDate) {
        LocalDate dateOfBirth = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return !dateOfBirth.isAfter(LocalDate.now().minusYears(18));
    }

    @Override
    public void creatFireStation(FireStationForm fireStationForm) throws IOException {
        List<FireStation> fireStations = findAllFireStations();
        FireStation fireStation = fireStationMapper(fireStationForm);
        boolean personExist = FireStationUtils.checkFireStationExists(fireStation, fireStations);

        if (personExist) throw new IllegalArgumentException("FireStation already exists");

        fireStations.add(fireStation);
        jsonData.writeDataToJson(fireStation);
    }

    @Override
    public void updateFireStation(FireStationForm fireStationForm) throws IOException {
        List<FireStation> fireStations = findAllFireStations();
        FireStation fireStation = fireStationMapper(fireStationForm);

        FireStation foundFireStation = FireStationUtils.findExactFireStation(fireStation, fireStations);
        boolean sameFireStation = FireStationUtils.sameFireStation(fireStation, fireStations);

        if (foundFireStation == null) throw new IllegalArgumentException("FireStation not found");
        if (sameFireStation) throw new IllegalArgumentException("Oops, no changes received !");

        for (int i = 0; i < fireStations.size(); i++) {
            if (fireStations.get(i).equals(foundFireStation))
                fireStations.set(i, fireStation);
        }
        jsonData.updateDataToJson(fireStations);
    }

    @Override
    public String deleteFireStation(FireStation fireStation) throws IOException {
        List<FireStation> fireStations = findAllFireStations();
        FireStation foundFireStation = FireStationUtils.findExactFireStation(fireStation, fireStations);
        if (foundFireStation == null) throw new IllegalArgumentException("FireStation not found");

        fireStations.removeIf(p -> p.equals(foundFireStation));
        jsonData.updateDataToJson(fireStations);
        return "The FireStation has been successfully deleted";
    }

    private FireStation fireStationMapper(FireStationForm fireStationForm) {
        return  modelMapper.map(fireStationForm, FireStation.class);
    }
}
