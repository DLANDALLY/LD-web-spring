package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.dto.fire.FireDto;
import com.dly.safetynet.dto.fire.PersonFireDto;
import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.services.interfaces.IChildAlert;
import com.dly.safetynet.services.interfaces.IFireStation;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FireServiceTest {

    @Mock
    private IPerson personService;
    @Mock
    private IMedicalRecord recordService;
    @Mock
    private IFireStation fireStationService;
    @Mock
    private IChildAlert childAlertService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private FireService fireService;
    private PersonDto personDto;

    @BeforeEach
    void setUp() {
        // Création d'une fause PersonDto
        personDto = new PersonDto();
        personDto.setFirstName("John");
        personDto.setLastName("Doe");
        personDto.setAddress("1509 Culver St");
        personDto.setPhone("841-874-6512");
    }

    @Test
    @Disabled
    void shouldGetFireAddress() {
        //Given
        MedicalRecord m1 = new MedicalRecord();
        m1.setFirstName("John");
        m1.setLastName("Doe");
        m1.setBirthdate("1990-01-01");

        String address = "1509 Culver St";

        when(personService.findPersonsDtoByAddress(address)).thenReturn(getPersonsDto());
        when(recordService.findMedicalRecordByFirstNameAndLastName(getPersonsDto())).thenReturn(getMedicalRecords());
        when(modelMapper.map(any(MedicalRecord.class),eq(PersonFireDto.class))).thenAnswer(invocation -> {
            MedicalRecord record = invocation.getArgument(0);
            PersonFireDto personFireDto = new PersonFireDto();
            personFireDto.setFirstName(record.getFirstName());
            personFireDto.setAge(35);
            return personFireDto;
        });
        when(childAlertService.calculateAge(m1.getBirthdate())).thenReturn(35);
        when(Integer.parseInt(fireStationService.findFireStationByAddress(address).getStation()))
                .thenReturn(3);

        //When
        FireDto result = fireService.getFireAddress(address);

        //Then
        assertEquals(address, result.getAddress());
        assertEquals("John", result.getPersonsFire().getFirst().getFirstName());
        assertEquals(35, result.getPersonsFire().getFirst().getAge());
        assertEquals(3, result.getStationNumber());
    }

    List<PersonDto> getPersonsDto(){
        // Création d'une fause PersonDto
        PersonDto p1 = new PersonDto();
        p1.setFirstName("John");
        p1.setLastName("Doe");
        p1.setAddress("1509 Culver St");
        p1.setPhone("841-874-6512");

        PersonDto p2 = new PersonDto();
        p2.setFirstName("Jane");
        p2.setLastName("Doe");
        p2.setAddress("1509 Culver St");
        p2.setPhone("841-874-6513");

        PersonDto p3 = new PersonDto();
        p3.setFirstName("Bob");
        p3.setLastName("Doe");
        p3.setAddress("1509 Culver St");
        p3.setPhone("841-874-6514");
        return List.of(p1, p2, p3);
    }

    List<MedicalRecord> getMedicalRecords() {
        // Création d'une faux MedicalRecord
        MedicalRecord m1 = new MedicalRecord();
        m1.setFirstName("John");
        m1.setLastName("Doe");
        m1.setBirthdate("1990-01-01");

        MedicalRecord m2 = new MedicalRecord();
        m2.setFirstName("Jane");
        m2.setLastName("Doe");
        m2.setBirthdate("1991-01-01");

        MedicalRecord m3 = new MedicalRecord();
        m3.setFirstName("Bob");
        m3.setLastName("Doe");
        m3.setBirthdate("1992-01-01");
        return List.of(m1, m2, m3);
    }
}