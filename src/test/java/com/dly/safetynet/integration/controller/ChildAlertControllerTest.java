package com.dly.safetynet.integration.controller;

import com.dly.safetynet.controllers.ChildAlertController;
import com.dly.safetynet.dto.childAlert.ChildDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChildAlertControllerTest {
    @Autowired
    private ChildAlertController childAlertController;

    @BeforeEach
    void setUp() {
    }

//    @Test
//    void shouldGetChildAlert() {
//        ResponseEntity childAlert = childAlertController.getChildAlert("892 Downing Ct");
//
//        ChildDto childDto = (ChildDto) childAlert.getBody();
//        assertEquals("200 OK", childDto);
//
//        //[ChildDto{firstName='Zach', lastName='Zemicks', age=8, familyMembers=[com.dly.safetynet.dto.childAlert.FamilyMembersDto@50f4b83d, com.dly.safetynet.dto.childAlert.FamilyMembersDto@10131289]}]
//
//    }
}