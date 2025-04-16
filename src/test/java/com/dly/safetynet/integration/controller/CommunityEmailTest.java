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
class CommunityEmailTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetCommunityEmailByCity() throws Exception {
        mockMvc.perform(get("/communityEmail")
                        .param("city", "Culver"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetCommunityEmailByCityBadRequest() throws Exception {
        mockMvc.perform(get("/communityEmail")
                        .param("cities", "123123"))
                .andExpect(status().isBadRequest());
    }
}