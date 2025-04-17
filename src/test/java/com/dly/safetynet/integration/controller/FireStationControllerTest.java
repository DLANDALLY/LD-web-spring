package com.dly.safetynet.integration.controller;

import com.dly.safetynet.form.FireStationForm;
import com.dly.safetynet.services.interfaces.IFireStation;
import com.dly.safetynet.validator.FireStationFormValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FireStationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private IFireStation fireStationService;
    @Mock
    private FireStationFormValidator fireStationFormValidator;

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
    void shouldCreatFirestationSuccessful() throws Exception {
        FireStationForm form = new FireStationForm();
        form.setAddress(generateNumber() +" Main St");
        form.setStation("11");

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldCreatFirestationBadRequest() throws Exception {
        FireStationForm form = new FireStationForm();
        form.setAddress("834 Binoc Ave");
        form.setStation("3");

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateFirestationSuccessful() throws Exception {
        FireStationForm form = new FireStationForm();
        form.setAddress("112 Steppes Pl");
        form.setStation(String.valueOf(generateNumber()));

        mockMvc.perform(put("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form)))
                .andExpect(status().isAccepted());
    }

    @Test
    void updateFirestationBadRequestl() throws Exception {
        FireStationForm form = new FireStationForm();
        form.setAddress("834 Binoc Ave");
        form.setStation("3");

        mockMvc.perform(put("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form)))
                .andExpect(status().isBadRequest());
    }

    int generateNumber() {
        Random random = new Random();
        return random.nextInt(100 - 1 + 1) + 1;
    }
}