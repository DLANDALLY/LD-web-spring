package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.floodStation.FloodStationDto;

import java.util.List;

public interface IFlood {
    FloodStationDto getFloodStations(List<String> stationNumbers);
}
