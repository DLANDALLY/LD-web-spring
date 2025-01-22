package com.dly.safetynet.dto.childAlert;

import java.util.List;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<FamilyMembersDto> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<FamilyMembersDto> familyMembers) {
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
