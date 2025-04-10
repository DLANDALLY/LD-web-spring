package com.dly.safetynet.entities;


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

    public FireStation(String address, String station) {
        this.address = address;
        this.station = station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FireStation{");
        sb.append("id=").append(id);
        sb.append(", address='").append(address).append('\'');
        sb.append(", station='").append(station).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
