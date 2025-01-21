package com.dly.safetynet.dto.floodStation;

import java.util.List;

public class HouseholdServedDto {
    private String address;
    private List<HouseholdMember> houseHolds;

    public HouseholdServedDto() {
    }

    public HouseholdServedDto(String address, List<HouseholdMember> houseHolds) {
        this.address = address;
        this.houseHolds = houseHolds;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<HouseholdMember> getHouseHolds() {
        return houseHolds;
    }

    public void setHouseHolds(List<HouseholdMember> houseHolds) {
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
