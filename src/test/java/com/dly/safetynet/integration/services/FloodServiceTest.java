package com.dly.safetynet.integration.services;

import com.dly.safetynet.dto.floodStation.FloodStationDto;
import com.dly.safetynet.services.FloodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FloodServiceTest {
    @Autowired
    private FloodService floodService;

    @Test
    void getFloodStations() {
        List<String> stationNumbers = List.of("1", "3", "4");

        FloodStationDto floodStationDto = floodService.getFloodStations(stationNumbers);
        String address = floodStationDto.getHousehold().getFirst().getAddress();
        String fullname = floodStationDto.getHousehold().getFirst().getHouseHolds().getFirst().getFullName();

        assertEquals("748 Townings Dr", address);
        assertEquals("Foster Shepard", fullname);
    }
}