package com.dly.safetynet.dto.childAlert;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChildDto {
    private String firstName;
    private String lastName;
    private int age;

    private List<FamilyMembersDto> familyMembers;

    public ChildDto() {
    }

    public ChildDto(String firstName, String lastName, int age, List<FamilyMembersDto> familyMembers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.familyMembers = familyMembers;
    }
}
