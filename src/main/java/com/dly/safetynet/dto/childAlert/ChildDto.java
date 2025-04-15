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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChildDto{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append(", familyMembers=").append(familyMembers);
        sb.append('}');
        return sb.toString();
    }
}
