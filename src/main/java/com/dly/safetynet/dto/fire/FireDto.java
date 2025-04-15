package com.dly.safetynet.dto.fire;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
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
