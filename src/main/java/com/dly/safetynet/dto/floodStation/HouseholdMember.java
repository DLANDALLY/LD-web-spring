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
}
