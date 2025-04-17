package com.dly.safetynet.integration.controller;

import com.dly.safetynet.form.MedicalRecordForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateMedicalRecordBadRequest() throws Exception {
        MedicalRecordForm medicalRecordForm = new MedicalRecordForm();
        medicalRecordForm.setFirstName("Damien");
        medicalRecordForm.setLastName("Poyd");
        medicalRecordForm.setBirthdate("01/08/1986");
        medicalRecordForm.setMedications(List.of("Medication"));
        medicalRecordForm.setAllergies(List.of("Allergie"));

        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicalRecordForm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateMedicalRecordBadRequest() throws Exception {
        MedicalRecordForm medicalRecordForm = new MedicalRecordForm();
        medicalRecordForm.setFirstName("Damien");
        medicalRecordForm.setLastName("Poyd");
        medicalRecordForm.setBirthdate("01/08/1986");
        medicalRecordForm.setMedications(List.of("Medication"));
        medicalRecordForm.setAllergies(List.of("Allergie"));

        mockMvc.perform(put("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicalRecordForm)))
                .andExpect(status().isBadRequest());
    }
}