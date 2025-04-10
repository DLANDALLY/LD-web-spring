package com.dly.safetynet.integration.services;

import com.dly.safetynet.dto.fire.FireDto;
import com.dly.safetynet.services.FireService;
import com.dly.safetynet.services.interfaces.IChildAlert;
import com.dly.safetynet.services.interfaces.IFireStation;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FireServiceTest {
    @Autowired
    private FireService fireService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getFireAddress() {
        String address = "892 Downing Ct";

        FireDto fireDto = fireService.getFireAddress(address);

        assertNotNull(fireDto);
        assertEquals("Sophia", fireDto.getPersonsFire().getFirst().getFirstName());
        assertEquals(37, fireDto.getPersonsFire().getFirst().getAge());
        assertEquals(2, fireDto.getStationNumber());
    }
}