package com.dly.safetynet.dto.floodStation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class HouseholdServedDto {
    private String address;
    private List<HouseholdMember> houseHolds;

    public HouseholdServedDto() {
    }
}
