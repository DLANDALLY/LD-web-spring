package com.dly.safetynet.dto;

public class MedicalRecordDto {
    private Long id;
    private String birthdate;

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
