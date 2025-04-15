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

    public FireStationResponse() {
    }

    public FireStationResponse(String station, List<PersonDto> persons, int adultCount, int childCount) {
        this.station = station;
        this.persons = persons;
        this.adultCount = adultCount;
        this.childCount = childCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FireStationResponse{");
        sb.append("station='").append(station).append('\'');
        sb.append(", persons=").append(persons);
        sb.append(", adultCount=").append(adultCount);
        sb.append(", childCount=").append(childCount);
        sb.append('}');
        return sb.toString();
    }
}
