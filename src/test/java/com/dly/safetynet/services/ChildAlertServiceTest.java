package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.dto.childAlert.ChildAlertDto;
import com.dly.safetynet.dto.childAlert.ChildDto;
import com.dly.safetynet.dto.childAlert.FamilyMembersDto;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChildAlertServiceTest {
    @Mock
    private IPerson personService;
    @Mock
    private IMedicalRecord recordService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ChildAlertService childAlertService;

    private ChildDto childDto;
    private PersonDto personDto;
    private MedicalRecord medicalRecord;


    @BeforeEach
    void setUp() {
        childDto = new ChildDto("John", "Doe", 8, families());

        // Création d'une fause PersonDto
        personDto = new PersonDto();
        personDto.setFirstName("John");
        personDto.setLastName("Doe");
        personDto.setAddress("1509 Culver St");
        personDto.setPhone("841-874-6512");

        // Création d'un faux MedicalRecord
        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("05/15/2017");
        medicalRecord.setMedications(Arrays.asList("aznol:350mg", "hydrapermazol:100mg"));
        medicalRecord.setAllergies(Arrays.asList("nillacilan"));
    }

    @Test
    void getChildAlert() {
        // given
        String address = "892 Downing Ct";
        List<PersonDto> persons = Arrays.asList(personDto);
        when(personService.findPersonsDtoByAddress(address)).thenReturn(persons);
        when(recordService.findMedicalRecordByFirstNameAndLastName(persons)).thenReturn(List.of(medicalRecord));
        when(modelMapper.map(any(MedicalRecord.class), eq(ChildDto.class))).thenAnswer(invocation -> childDto);
        when(modelMapper.map(any(ChildDto.class), eq(FamilyMembersDto.class))).thenAnswer(invocation -> {
            ChildDto child = invocation.getArgument(0);
            FamilyMembersDto familyMember = new FamilyMembersDto();
            familyMember.setFirstName(child.getFirstName());
            familyMember.setLastName(child.getLastName());
            familyMember.setAge(child.getAge());
            return familyMember;
        });

        // when
        ChildAlertDto childAlertDto = childAlertService.getChildAlert(address);

        // then
        assertEquals("John", childAlertDto.getChildren().getFirst().getFirstName());
        assertEquals("Doe", childAlertDto.getChildren().getFirst().getLastName());
    }

    @Test
    void shouldThrowExceptionWhenAddressIsBlank(){
        // then
        assertThrows(IllegalArgumentException.class,()-> childAlertService.getChildAlert(""));
    }

    @Test
    void shouldThrowExceptionWhenNoPersonsFound(){
        // given
        String address = "1509 Culver St";
        when(personService.findPersonsDtoByAddress(address)).thenReturn(new ArrayList<>());

        // then
        assertThrows(RuntimeException.class,()-> childAlertService.getChildAlert(address));
    }

    @Test
    void shouldThrowExceptionWhenNoMedicalRecordsFound(){
        //given
        String address = "1509 Culver St";
        List<PersonDto> persons = Arrays.asList(personDto);
        when(personService.findPersonsDtoByAddress(address)).thenReturn(persons);
        when(recordService.findMedicalRecordByFirstNameAndLastName(persons)).thenReturn(new ArrayList<>());

        // when
        Exception exception = assertThrows(NoSuchElementException.class,()-> childAlertService.getChildAlert(address));

        // then
        assertThrows(NoSuchElementException.class,()-> childAlertService.getChildAlert(address));
        assertEquals("No children living at this address", exception.getMessage());
    }

    @Test
    void shouldCalculateAge(){
        // given
        String birthdate = "05/15/2015";
        int expectedAge = 10;

        // when
        int actualAge = childAlertService.calculateAge(birthdate);

        // then
        assertEquals(expectedAge, actualAge);
    }

    List<FamilyMembersDto> families(){
        FamilyMembersDto f1 = new FamilyMembersDto("Sophia", "Doe",37);
        FamilyMembersDto f2 = new FamilyMembersDto("Warren", "Doe",40);
        FamilyMembersDto f3 = new FamilyMembersDto("zack", "Doe",8);
        List<FamilyMembersDto> members = Arrays.asList(f1, f2, f3);
        return members;
    }
}