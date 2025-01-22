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
    private JsonDataService jsonData;
    @Autowired
    private IPerson personService;
    @Autowired
    private IMedicalRecord medicalRecordService;



    /**
     * http://localhost:8080/firestation?stationNumber=<station_number>
     * Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers
     * correspondante. Donc, si le numéro de station = 1, elle doit renvoyer les habitants
     * couverts par la station numéro 1. La liste doit inclure les informations spécifiques
     * suivantes : prénom, nom, adresse, numéro de téléphone.
     * De plus, elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants
     * (tout individu âgé de 18 ans ou moins) dans la zone desservie.
     */
    @Override
    public FireStationResponse personCoverageByFireStation(String station) {
        if (station == null || station.isBlank()) throw new IllegalArgumentException("Station number cannot be null or blank");

        List<String> addresses = findAdresses(station);
        if (addresses.isEmpty()) throw new IllegalArgumentException("No address is covered by this station number");

        List<PersonDto> persons = getPersonsDto(addresses);
        if (persons.isEmpty()) throw new IllegalArgumentException("No residents are covered by this station number");

        List<MedicalRecord> birthdays = getBirthdays(persons);
        if (birthdays.isEmpty()) throw new IllegalArgumentException("The dates of birth are not entered correctly. ");

        int adultCount = getAdultCount(birthdays);
        int childCount = birthdays.size() - adultCount;

        return new FireStationResponse(station, persons, adultCount, childCount);
    }

    @Override
    public List<String> findAdresses(String station) {
        return jsonData.getFirestations().stream()
               .filter(fs -> fs.getStation().equals(station))
               .map(FireStation::getAddress)
               .collect(Collectors.toList());
    }

    @Override
    public FireStation findFireStationByAddress(String address){
        return jsonData.getFirestations()
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
                .filter(medicalRecord -> isOver18(medicalRecord.getBirthdate()))
                .count();
    }

    @Override
    public boolean isOver18(String birthDate) {
        LocalDate dateOfBirth = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return !dateOfBirth.isAfter(LocalDate.now().minusYears(18));
    }
}
