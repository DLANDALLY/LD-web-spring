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

    public HouseholdServedDto(String address, List<HouseholdMember> houseHolds) {
        this.address = address;
        this.houseHolds = houseHolds;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HouseholdServedDto{");
        sb.append("address='").append(address).append('\'');
        sb.append(", houseHolds=").append(houseHolds);
        sb.append('}');
        return sb.toString();
    }
}
