package com.dly.safetynet.dto.fire;

import java.util.List;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
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
