package com.dly.safetynet.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FireStationResponse {
    private String station;
    private List<PersonDto> persons;
    private int adultCount;
    private int childCount;

    public FireStationResponse(String station, List<PersonDto> persons, int adultCount, int childCount) {
        this.station = station;
        this.persons = persons;
        this.adultCount = adultCount;
        this.childCount = childCount;
    }
}
