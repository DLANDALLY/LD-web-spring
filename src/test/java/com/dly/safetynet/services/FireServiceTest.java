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
import org.mockito.ArgumentMatchers;
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

    @BeforeEach
    void setUp() {}

//    @Test
//    @Disabled
//    void shouldGetFireAddress() {
//        //TODO : Test unitaire à faire et ces exceptions
//        //Given
//        String address = getPersonsDto().getFirst().getAddress();
//        FireStation fireStation = new FireStation(1L, address, "3");
//
//        when(personService.findPersonsDtoByAddress(address)).thenReturn(getPersonsDto());
//        when(recordService.findMedicalRecordByFirstNameAndLastName(
//                ArgumentMatchers.argThat(persons -> persons.stream().anyMatch(p -> "John".equals(p.getFirstName())))
//        )).thenReturn(getMedicalRecords());
//        //when(recordService.findMedicalRecordByFirstNameAndLastName(getPersonsDto())).thenReturn(getMedicalRecords());
//        when(modelMapper.map(any(MedicalRecord.class),eq(PersonFireDto.class))).thenAnswer(invocation -> {
//            MedicalRecord record = invocation.getArgument(0);
//            PersonFireDto personFireDto = new PersonFireDto();
//            personFireDto.setFirstName(getPersonsDto().getFirst().getFirstName());
//            personFireDto.setAge(35);
//            return personFireDto;});
//        when(childAlertService.calculateAge(getMedicalRecords().getFirst().getBirthdate())).thenReturn(35);
//        when(fireStationService.findFireStationByAddress(address)).thenReturn(fireStation);
//        //when(fireStation.getStation()).thenReturn(3);
//
//        //When
//        FireDto result = fireService.getFireAddress(address);
//
//        //Then
//        assertEquals(address, result.getAddress());
//        assertEquals("John", result.getPersonsFire().getFirst().getFirstName());
//        assertEquals(35, result.getPersonsFire().getFirst().getAge());
//        assertEquals(3, result.getStationNumber());
//    }

    List<PersonDto> getPersonsDto(){
        return List.of(
                new PersonDto("John","Doe", "1509 Culver St", "841-874-6512"),
                new PersonDto("Jane", "Doe", "1509 Culver St","841-874-6513"),
                new PersonDto("Bob", "Doe", "1509 Culver St", "841-874-6514"));
    }

    List<MedicalRecord> getMedicalRecords() {
        return List.of(
                new MedicalRecord("John", "Doe", "1990-01-20",null,null),
                new MedicalRecord("Jane", "Doe", "1991-04-01",null,null),
                new MedicalRecord("Bob", "Doe", "1992-12-17",null,null));
    }
}