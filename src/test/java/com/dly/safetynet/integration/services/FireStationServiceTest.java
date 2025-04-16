package com.dly.safetynet.integration.services;

import com.dly.safetynet.dto.FireStationResponse;
import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.form.FireStationForm;
import com.dly.safetynet.services.FireStationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FireStationServiceTest {
    @Autowired
    private FireStationService fireStationService;

    @Test
    void shouldFindAllFireStations() {
        List<FireStation> fireStations = fireStationService.findAllFireStations();

        assertNotNull(fireStations);
        assertEquals("1509 Culver St", fireStations.getFirst().getAddress());
        assertEquals("3", fireStations.getFirst().getStation());
    }

    @Test
    void shouldPersonCoverageByFireStation() {
        FireStationResponse fireStationResponse = fireStationService.personCoverageByFireStation("2");

        assertNotNull(fireStationResponse);
        assertEquals("Jonanathan", fireStationResponse.getPersons().getFirst().getFirstName());
    }

    @Test
    void shouldFindAddress() {
        List<String> address = fireStationService.findAddress("2");

        assertNotNull(address);
        assertEquals("29 15th St", address.getFirst());
        assertEquals(3, address.size());
    }

    @Test
    void shouldFindFireStationByAddress() {
        FireStation station = fireStationService.findFireStationByAddress("29 15th St");

        assertNotNull(station);
        assertEquals("2", station.getStation());
    }

    @Test
    void shouldGetPersonsDto() {
        List<PersonDto> personDtos = fireStationService.getPersonsDto(getAddressList());

        assertNotNull(personDtos);
        assertEquals("Jonanathan", personDtos.getFirst().getFirstName());
        assertEquals("29 15th St", personDtos.getFirst().getAddress());
        assertEquals("841-874-6513", personDtos.getFirst().getPhone());
    }

    @Test
    void shouldGetPersonsDtoSet() {
        List<PersonDto> personDtos = fireStationService.getPersonsDto(getAddressSet());

        assertEquals(5, personDtos.size());
    }

    @Test
    void shouldGetBirthdays() {
        List<MedicalRecord> birthdays = fireStationService.getBirthdays(getPersonsDto());

        assertNotNull(birthdays);
        assertEquals("John", birthdays.getFirst().getFirstName());
        assertEquals("03/06/1984", birthdays.getFirst().getBirthdate());
    }

    @Test
    void getAdultCount() {
        int adults = fireStationService.getAdultCount(getMedicalRecords());

        assertEquals(2, adults);
    }

    @Test
    void isOver18() {
        assertTrue(fireStationService.isOver18("03/06/1984"));
    }

    List<PersonDto> getPersonsDto(){
            return List.of(
                new PersonDto("John","Boyd", "1509 Culver St", "841-874-6512"),
                new PersonDto("Jacob", "Boyd", "1509 Culver St","841-874-6513"),
                new PersonDto("Tenley", "Boyd", "1509 Culver St", "841-874-6514"));
    }

    @Test
    void shouldCreatFireStation(){
        List<FireStation> fireStations = fireStationService.findAllFireStations();
        int number = generateNumber();
        FireStationForm fireStationForm = new FireStationForm();
        fireStationForm.setAddress(number +" rue de la paix");
        fireStationForm.setStation("3");

        fireStationService.creatFireStation(fireStationForm);

        assertEquals(number +" rue de la paix", fireStations.getLast().getAddress());
        assertEquals("3", fireStations.getLast().getStation());
    }

    @Test
    void shouldUpdateFireStation() {
        List<FireStation> fireStations = fireStationService.findAllFireStations();
        String numberStr = String.valueOf(generateNumber());

        FireStationForm fireStationForm = new FireStationForm();
        fireStationForm.setAddress(fireStations.getLast().getAddress());
        fireStationForm.setStation(String.valueOf(numberStr));

        fireStationService.updateFireStation(fireStationForm);

        assertEquals(numberStr, fireStations.getLast().getStation());
    }

    @Test
    void shouldDeleteFireStation() {
        List<FireStation> fireStations = fireStationService.findAllFireStations();
        FireStation fireStation = new FireStation();
        fireStation.setAddress(fireStations.getLast().getAddress());
        fireStation.setStation(fireStations.getLast().getStation());

        assertEquals("The FireStation has been successfully deleted",fireStationService.deleteFireStation(fireStation));
    }

    List<String> getAddressList(){
        return List.of(
                "29 15th St",
                "892 Downing Ct",
                "951 LoneTree Rd");
    }

    Set<String> getAddressSet(){
        return Set.of(
                "29 15th St",
                "892 Downing Ct",
                "951 LoneTree Rd");
    }

    List<MedicalRecord> getMedicalRecords() {
        return List.of(
                new MedicalRecord("John", "Boyd", "03/06/1984",null,null),
                new MedicalRecord("Jacob", "Boyd", "03/06/1989",null,null),
                new MedicalRecord("Tenley", "Boyd", "02/18/2012",null,null));
    }

    int generateNumber() {
        Random random = new Random();
        return random.nextInt(1000 - 1 + 1) + 1;
    }
}