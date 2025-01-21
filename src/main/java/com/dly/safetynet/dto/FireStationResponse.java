package com.dly.safetynet.dto;


import java.util.List;


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

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public List<PersonDto> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonDto> persons) {
        this.persons = persons;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
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
