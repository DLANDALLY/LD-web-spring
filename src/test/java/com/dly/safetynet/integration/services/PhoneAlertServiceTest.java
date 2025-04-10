package com.dly.safetynet.integration.services;

import com.dly.safetynet.dto.phoneAlert.PhoneAlertDto;
import com.dly.safetynet.services.PhoneAlertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneAlertServiceTest {
    @Autowired
    private PhoneAlertService phoneAlertService;

    @Test
    void getPhoneAlert() {
        List<PhoneAlertDto> phoneAlertDtos = phoneAlertService.getPhoneAlert(5);

        assertEquals("841-874-6544", phoneAlertDtos.getFirst().getPhone());
        assertEquals("841-874-6741", phoneAlertDtos.get(1).getPhone());
    }
}