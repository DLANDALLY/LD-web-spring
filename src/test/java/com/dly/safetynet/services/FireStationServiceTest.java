package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    void setUp() {
        //MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAllFireStations() {
        FireStation f1 = new FireStation(1L, "1 parc de la mairie", "1");
        FireStation f2 = new FireStation(2L, "2 parc de la mairie", "2");
        FireStation f3 = new FireStation(3L, "3 parc de la mairie", "3");
        List<FireStation> fireServiceList = List.of(f1, f2, f3);

        when(jsonData.getFirestations()).thenReturn(fireServiceList);

        assertEquals(3 , fireStationService.findAllFireStations().size());
    }

    @Test
    void personCoverageByFireStation() {
        String station = "2";
        List<String> address = List.of("29 15th St", "892 Downing Ct", "951 LoneTree Rd");

        List<PersonDto> persons = List.of(
                new PersonDto("Jonanathan", "Marrack", "29 15th St", "841-874-6513"),
                new PersonDto("Sophia", "Zemicks", "892 Downing Ct", "841-874-7878"),
                new PersonDto("Warren", "Zemicks", "892 Downing Ct", "841-874-7512"),
                new PersonDto("Zach", "Zemicks", "892 Downing Ct", "841-874-7512"),
                new PersonDto("Eric", "Cadigan", "951 LoneTree Rd", "841-874-7458")
        );

        when(fireStationService.findAddress(station)).thenReturn(address);
        when(fireStationService.getPersonsDto(address)).thenReturn(persons);
        //when(fireStationService.getBirthdays(persons)).thenReturn();
    }

    @Test
    void shouldFindAddress() {
        String station = "2";
        List<String> address = List.of("29 15th St", "892 Downing Ct", "951 LoneTree Rd");
        when(fireStationService.findAddress(station)).thenReturn(address);

        //assertEquals(3, );
    }

    @Test
    void findFireStationByAddress() {
    }

    @Test
    void getPersonsDto() {
    }

    @Test
    void testGetPersonsDto() {
    }

    @Test
    void getBirthdays() {
    }

    @Test
    void getAdultCount() {
    }

    @Test
    void isOver18() {
    }



}