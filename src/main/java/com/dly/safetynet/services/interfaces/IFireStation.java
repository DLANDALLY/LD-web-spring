package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.Person;

import java.util.List;
import java.util.Map;

public interface IFireStation {
    Map<FireStation, List<Person>> personCoverageByFireStation(String station);

    List<FireStation> getFireStationByStation(String station);
}
