package com.dly.safetynet.dto.childAlert;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FamilyMembersDto {
    private String firstName;
    private String lastName;
    private int age;

    public FamilyMembersDto() {
    }

    public FamilyMembersDto(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

}
