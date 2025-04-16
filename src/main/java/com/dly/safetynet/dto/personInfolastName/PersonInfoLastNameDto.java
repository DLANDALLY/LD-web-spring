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
}
