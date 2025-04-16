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
}
