package com.dly.safetynet.services;

import com.dly.safetynet.services.interfaces.IPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CommunityEmailServiceTest {
    @Mock
    private IPerson personService;
    @InjectMocks
    private CommunityEmailService communityEmailService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldGetCommunityEmailByCity() {
        // Given
        String city = "Culver";
        when(personService.findEmailByCity(city)).thenReturn(emails());

        // Then
        assertEquals(emails(), communityEmailService.getCommunityEmailByCity(city));
    }

    List<String> emails(){
        return List.of("john.doe@gmail.com", "jane.doe@gmail.com",  "jaboyd@email.com", "drk@email.com");
    }
}