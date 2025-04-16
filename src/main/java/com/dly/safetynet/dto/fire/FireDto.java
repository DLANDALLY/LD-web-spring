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

    public FireDto(String address, List<PersonFireDto> personsFire, int stationNumber) {
        this.address = address;
        this.personsFire = personsFire;
        this.stationNumber = stationNumber;
    }
}
