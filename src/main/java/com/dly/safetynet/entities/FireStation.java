package com.dly.safetynet.entities;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FireStation {
    private Long id;
    private String address;
    private String station;

    public FireStation() {
    }

    public FireStation(Long id, String address, String station) {
        this.id = id;
        this.address = address;
        this.station = station;
    }
}
