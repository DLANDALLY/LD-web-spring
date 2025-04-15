package com.dly.safetynet.dto.personInfolastName;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PersonInfoLastNameDto {
    private String lastName;
    private String address;
    private int age;
    private String email;
    private List<String> medications;
    private List<String> allergies;

    public PersonInfoLastNameDto() {
    }

    public PersonInfoLastNameDto(String lastName, String address, int age, String email, List<String> medications, List<String> allergies) {
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.email = email;
        this.medications = medications;
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonInfoLastNameDto{");
        sb.append("lastName='").append(lastName).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", age=").append(age);
        sb.append(", email='").append(email).append('\'');
        sb.append(", medications=").append(medications);
        sb.append(", allergies=").append(allergies);
        sb.append('}');
        return sb.toString();
    }
}
