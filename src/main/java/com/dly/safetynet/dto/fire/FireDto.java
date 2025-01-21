package com.dly.safetynet.dto.fire;

import java.util.List;

public class FireDto {
    private String address;
    private List<PersonFireDto> personsFire;
    private int stationNumber;

    public FireDto() {
    }

    public FireDto(String address, List<PersonFireDto> personsFire, int stationNumber) {
        this.address = address;
        this.personsFire = personsFire;
        this.stationNumber = stationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonFireDto> getPersonsFire() {
        return personsFire;
    }

    public void setPersonsFire(List<PersonFireDto> personsFire) {
        this.personsFire = personsFire;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FireDto{");
        sb.append("address='").append(address).append('\'');
        sb.append(", personsFire=").append(personsFire);
        sb.append(", stationNumber=").append(stationNumber);
        sb.append('}');
        return sb.toString();
    }
}
