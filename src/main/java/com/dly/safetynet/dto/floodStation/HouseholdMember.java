package com.dly.safetynet.dto.floodStation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class HouseholdMember {
    private String fullName;
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;
    private String address;

    public HouseholdMember() {
    }

    public HouseholdMember(String fullName, String phone, int age, List<String> medications, List<String> allergies) {
        this.fullName = fullName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HouseholdMember{");
        sb.append("fullName='").append(fullName).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", age=").append(age);
        sb.append(", medications=").append(medications);
        sb.append(", allergies=").append(allergies);
        sb.append(", address='").append(address).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
