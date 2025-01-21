package com.dly.safetynet.dto.floodStation;

import java.util.List;

public class FloodStationDto {
    private List<HouseholdServedDto> household;

    public FloodStationDto(List<HouseholdServedDto> household) {
        this.household = household;
    }

    public List<HouseholdServedDto> getHousehold() {
        return household;
    }

    public void setHousehold(List<HouseholdServedDto> household) {
        this.household = household;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FloodStationDto{");
        sb.append("household=").append(household);
        sb.append('}');
        return sb.toString();
    }
}
