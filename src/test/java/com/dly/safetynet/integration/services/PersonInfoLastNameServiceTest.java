package com.dly.safetynet.integration.services;

import com.dly.safetynet.dto.personInfolastName.PersonInfoLastNameDto;
import com.dly.safetynet.services.PersonInfoLastNameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonInfoLastNameServiceTest {
    @Autowired
    PersonInfoLastNameService personInfoLastNameService;

    @Test
    void getPersonInfoLastName() {
        List<PersonInfoLastNameDto> personInfoLastNameDtos = personInfoLastNameService.getPersonInfoLastName("Boyd");

        assertEquals(6, personInfoLastNameDtos.size());
        assertEquals("Boyd", personInfoLastNameDtos.getFirst().getLastName());
    }
}