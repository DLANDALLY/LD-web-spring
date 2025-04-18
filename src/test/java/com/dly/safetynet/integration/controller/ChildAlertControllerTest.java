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
class ChildAlertControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetChildAlert() throws Exception {
        mockMvc.perform(get("/childAlert")
                        .param("address", "892 Downing Ct"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetChildAlertBadRequest() throws Exception {
        mockMvc.perform(get("/childAlert")
                        .param("addressee", "892 Downing Ct"))
                .andExpect(status().isBadRequest());
    }
}