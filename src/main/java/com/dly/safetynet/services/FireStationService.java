package com.dly.safetynet.services;

import com.dly.safetynet.dto.FireStationResponse;
import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.services.interfaces.IFireStation;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
