package com.dly.safetynet.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MedicalRecord {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    public MedicalRecord() {
    }

    public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MedicalRecord{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthdate='").append(birthdate).append('\'');
        sb.append(", medications=").append(medications);
        sb.append(", allergies=").append(allergies);
        sb.append('}');
        return sb.toString();
    }
}
