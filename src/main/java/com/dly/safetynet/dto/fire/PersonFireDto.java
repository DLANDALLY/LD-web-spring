package com.dly.safetynet.dto.fire;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PersonFireDto {
    private String firstName;
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;

    public PersonFireDto() {
    }

    public PersonFireDto(String firstName, String phone, int age, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonFireDto{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", age=").append(age);
        sb.append(", medications=").append(medications);
        sb.append(", allergies=").append(allergies);
        sb.append('}');
        return sb.toString();
    }
}
