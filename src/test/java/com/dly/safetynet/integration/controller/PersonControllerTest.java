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

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
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
    void createPerson() throws Exception {
        PersonForm personForm = new PersonForm();
        personForm.setFirstName("John");
        personForm.setLastName("Doe");
        personForm.setAddress("123 Main St");
        personForm.setCity("Montreal");
        personForm.setPhone("123-456-7890");
        personForm.setZip("H1A1A1");
        personForm.setEmail("john.doe@example.com");

        mockMvc.perform((org.springframework.test.web.servlet.RequestBuilder) post("/person") // adapte l'URL si elle est différente
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.valueOf(objectMapper.writeValueAsString(personForm))))
                .andExpect(status().isCreated())
                .andExpect(content().string("Great! John has been successfully saved"));
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePerson() {
    }
}