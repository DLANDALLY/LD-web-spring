package com.dly.safetynet.integration.controller;

import com.dly.safetynet.form.PersonForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreatePersonSuccessful() throws Exception {
        PersonForm personForm = new PersonForm();
        personForm.setFirstName("John");
        personForm.setLastName("Doe");
        personForm.setAddress("123 Main St");
        personForm.setCity("Montreal");
        personForm.setPhone(generateNumber()+"-456-7890");
        personForm.setZip("H1A1A1");
        personForm.setEmail("john.doe"+generateNumber()+"@example.com");

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personForm)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Great! John has been successfully saved"));
    }

    @Test
    void shouldCreatePersonBadRequest() throws Exception {
        PersonForm personForm = new PersonForm();
        personForm.setFirstName("Zach");
        personForm.setLastName("Zemicks");
        personForm.setAddress("892 Downing Ct");
        personForm.setCity("Culver");
        personForm.setPhone("841-874-7512");
        personForm.setZip("97451");
        personForm.setEmail("zarc@email.com");

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personForm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdatePersonSuccessful() throws Exception {
        PersonForm personForm = new PersonForm();
        personForm.setFirstName("Tenley");
        personForm.setLastName("Boyd");
        personForm.setAddress(generateNumber()+" Main St");
        personForm.setCity("Montreal");
        personForm.setPhone("841-874-6512");
        personForm.setZip("H1"+generateNumber()+"A1");
        personForm.setEmail("tenz@email.com");

        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personForm)))
                .andExpect(status().isAccepted());
    }

    @Test
    void shouldUpdatePersonBadRequest() throws Exception {
        PersonForm personForm = new PersonForm();
        personForm.setFirstName("Damien");
        personForm.setLastName("Boyd");
        personForm.setAddress(generateNumber()+" Main St");
        personForm.setCity("Montreal");
        personForm.setPhone("841-874-6512");
        personForm.setZip("H1"+generateNumber()+"A1");
        personForm.setEmail("tenz@email.com");

        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personForm)))
                .andExpect(status().isBadRequest());
    }

    int generateNumber() {
        Random random = new Random();
        return random.nextInt(100 - 1 + 1) + 1;
    }
}