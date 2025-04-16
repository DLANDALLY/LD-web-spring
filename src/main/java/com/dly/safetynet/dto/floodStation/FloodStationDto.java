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

}
