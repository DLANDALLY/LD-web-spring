package com.dly.safetynet.dto.floodStation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FloodStationDto {
    private List<HouseholdServedDto> household;

    public FloodStationDto(List<HouseholdServedDto> household) {
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
