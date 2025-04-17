package com.dly.safetynet.integration.services;

import com.dly.safetynet.services.interfaces.IPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommunityEmailServiceTest {
    @Autowired
    private IPerson personService;

    @Test
    void getCommunityEmailByCity() {
        List<String> emails = personService.findEmailByCity("Culver");

        String email = emails.stream().filter(e -> e.equals("drk@email.com")).findFirst().get();

        assertNotNull(emails);
        assertEquals("drk@email.com", email);
    }
}