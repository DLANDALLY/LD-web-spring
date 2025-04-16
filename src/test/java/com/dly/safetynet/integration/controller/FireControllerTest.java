package com.dly.safetynet.integration.controller;

import com.dly.safetynet.services.interfaces.IFire;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FireControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetFireAddress() throws Exception {
        mockMvc.perform(get("/fire")
                        .param("address", "892 Downing Ct"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetFireAddressBadRequest() throws Exception {
        mockMvc.perform(get("/fire")
                        .param("address", "Downing Ct"))
                .andExpect(status().isBadRequest());
    }
}