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
class PhoneAlertControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetPhoneAlert() throws Exception {
        mockMvc.perform(get("/phoneAlert")
                        .param("firestation", "5"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetPhoneAlertBadRequest() throws Exception {
        mockMvc.perform(get("/phoneAlert")
                        .param("firestation", "555555"))
                .andExpect(status().isBadRequest());
    }
}