package com.dly.safetynet.dto;


import jakarta.persistence.Id;

public class MedicalRecordDto {
    @Id
    private Long id;
    private String birthdate;

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
