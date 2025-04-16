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
class PersonInfoLastNameControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void getPersonInfoLastName() throws Exception {
        mockMvc.perform(get("/")
                        .param("personInfolastName", "Boyd"))
                .andExpect(status().isOk());
    }

    @Test
    void getPersonInfoLastNameBadRequest() throws Exception {
        mockMvc.perform(get("/info")
                        .param("personInfolastName", "Poiuytr"))
                .andExpect(status().isNotFound());
    }

}