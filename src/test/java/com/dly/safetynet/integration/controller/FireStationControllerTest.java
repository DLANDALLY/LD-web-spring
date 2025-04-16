package com.dly.safetynet.integration.controller;

import com.dly.safetynet.form.FireStationForm;
import com.dly.safetynet.services.interfaces.IFireStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FireStationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IFireStation fireStationService;

    @Test
    void shouldGetFirestation() throws Exception {
        mockMvc.perform(get("/firestation")
                        .param("stationNumber", "2"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetFirestationBadRequest() throws Exception {
        mockMvc.perform(get("/firestation")
                        .param("stationNumber", "2789456"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldCreateFirestation() throws Exception {
    }

    @Test
    void updateFirestation() {
    }

    @Test
    void deleteFirestation() {
    }
}