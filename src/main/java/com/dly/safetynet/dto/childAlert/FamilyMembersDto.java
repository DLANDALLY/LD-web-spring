package com.dly.safetynet.dto.childAlert;

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
}

//TODO : attribut redondant / faire de l'heritage
//TODO : VOire dans tous les DTO toute rendondance
//TODO :