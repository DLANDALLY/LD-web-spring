package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.FireStationResponse;
import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.MedicalRecord;

import java.util.List;
import java.util.Set;

public interface IFireStation {
    List<FireStation> findAllFireStations();

    FireStationResponse personCoverageByFireStation(String station);

    List<String> findAdresses(String station);

    FireStation findFireStationByAddress(String address);

    List<PersonDto> getPersonsDto(List<String> addresses);

    List<PersonDto> getPersonsDto(Set<String> addresses);

    List<MedicalRecord> getBirthdays(List<PersonDto> persons);

    int getAdultCount(List<MedicalRecord> medicalRecords);

    boolean isOver18(String birthDate);
}
