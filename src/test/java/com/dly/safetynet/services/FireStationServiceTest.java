package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.dto.fire.PersonFireDto;
import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FireStationServiceTest {

    @Mock
    private IPerson personService;
    @Mock
    private IMedicalRecord medicalRecordService;
    @Mock
    private JsonDataService jsonData;
    @InjectMocks
    private FireStationService fireStationService;

    @BeforeEach
    void setUp() {}

    @Test
    void shouldFindAllFireStations() {
        when(jsonData.getFirestations()).thenReturn(findAllFireStations());

        assertEquals(4 , fireStationService.findAllFireStations().size());
    }

    @Test
    void shouldFindAddress() {
        String station = "2";

        when(fireStationService.findAllFireStations()).thenReturn(findAllFireStations());

        List<String> result = fireStationService.findAddress(station);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("29 15th St", result.getFirst());
    }

    @Test
    void findFireStationByAddress() {
        when(fireStationService.findAllFireStations()).thenReturn(findAllFireStations());

        FireStation fireStation = fireStationService.findFireStationByAddress("29 15th St");

        assertNotNull(fireStation);
        assertEquals("2", fireStation.getStation());
    }

    @Test
    void shouldListPersonsDto() {
        List<String> address = List.of(
                findAllFireStations().get(0).getAddress(),
                findAllFireStations().get(1).getAddress(),
                findAllFireStations().get(2).getAddress(),
                findAllFireStations().get(3).getAddress());

        when(personService.findPersonsDtoByAddress(findAllFireStations().getFirst().getAddress())).thenReturn(getPersonsDto());

        List<PersonDto> personDtos = fireStationService.getPersonsDto(address);

        assertNotNull(personDtos);
        assertEquals(3, personDtos.size());
        assertEquals("841-874-6512", personDtos.getFirst().getPhone());
    }

    @Test
    void shouldSetPersonsDto() {
        Set<String> address = Set.of("1 parc de la mairie");

        when(personService.findPersonsDtoByAddress(findAllFireStations().getFirst().getAddress())).thenReturn(getPersonsDto());

        List<PersonDto> personDtos = fireStationService.getPersonsDto(address);

        assertNotNull(personDtos);
        assertEquals(3, personDtos.size());
        assertEquals("841-874-6512", personDtos.getFirst().getPhone());
    }

    @Test
    void shouldGetBirthdays() {
        List<PersonDto> personDtos = getPersonsDto();
        when(medicalRecordService.findMedicalRecordByFirstNameAndLastName(personDtos)).thenReturn(getMedicalRecords());

        List<MedicalRecord> medicalRecords = fireStationService.getBirthdays(personDtos);

        assertNotNull(medicalRecords);
        assertEquals("1990-01-20", medicalRecords.getFirst().getBirthdate());
    }

//    @Test
//    void getAdultCount() {
//        List<MedicalRecord> medicalRecords = List.of(
//                new MedicalRecord("John", "Doe", "1990-01-20",null,null),
//                new MedicalRecord("Jane", "Doe", "1991-04-01",null,null),
//                new MedicalRecord("Bob", "Doe", "2020-12-17",null,null));
//
//        var adults = fireStationService.getAdultCount(medicalRecords);
//
//        assertEquals(2, adults);
//    }
//
//    @Test
//    void isOver18() {
//        String birthDate = "1990-01-20";
//        assertTrue(fireStationService.isOver18(birthDate));
//    }

    List<FireStation> findAllFireStations() {
        return List.of(
                new FireStation(1L, "1 parc de la mairie", "1"),
                new FireStation(2L, "29 15th St", "2"),
                new FireStation(3L, "3 parc de la marie", "2"),
                new FireStation(4L,  "892 Downing Ct", "3"));
    }

    List<PersonDto> getPersonsDto(){
        return List.of(
                new PersonDto("John","Doe", "1 parc de la mairie", "841-874-6512"),
                new PersonDto("Jane", "Doe", "1 parc de la mairie","841-874-6513"),
                new PersonDto("Bob", "Doe", "1 parc de la mairie", "841-874-6514"));
    }

    List<MedicalRecord> getMedicalRecords() {
        return List.of(
                new MedicalRecord("John", "Doe", "1990-01-20",null,null),
                new MedicalRecord("Jane", "Doe", "1991-04-01",null,null),
                new MedicalRecord("Bob", "Doe", "2020-12-17",null,null));
    }
}