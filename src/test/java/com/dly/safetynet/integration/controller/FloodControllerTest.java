package com.dly.safetynet.integration.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FloodControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetFloodStations() throws Exception {
        mockMvc.perform(get("/flood/stations")
                        .param("stations", "1, 3, 4"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetFloodStationsBadRequest() throws Exception {
        mockMvc.perform(get("/flood/stations")
                        .param("stations", "Azerty"))
                .andExpect(status().isOk());
    }
}