package com.dly.safetynet.integration.services;

import com.dly.safetynet.dto.childAlert.ChildAlertDto;
import com.dly.safetynet.integration.config.JsonDatabaseTest;
import com.dly.safetynet.services.ChildAlertService;
import com.dly.safetynet.services.MedicalRecordService;
import com.dly.safetynet.services.PersonService;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChildAlertServiceTest {
    @Autowired
    private ChildAlertService childAlertService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getChildAlert() {
        ChildAlertDto childAlertDto = childAlertService.getChildAlert("892 Downing Ct");

        assertNotNull(childAlertDto);
        assertEquals("Zach", childAlertDto.getChildren().getFirst().getFirstName());
        assertEquals("Sophia", childAlertDto.getChildren().getFirst().getFamilyMembers().getFirst().getFirstName());
        assertEquals(37, childAlertDto.getChildren().getFirst().getFamilyMembers().getFirst().getAge());
    }
}