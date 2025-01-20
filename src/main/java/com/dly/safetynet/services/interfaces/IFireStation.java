package com.dly.safetynet.services.interfaces;


import com.dly.safetynet.dto.FireStationResponse;
import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.dto.phoneAlert.PhoneAlertDto;
import com.dly.safetynet.entities.MedicalRecord;

import java.util.List;

public interface IFireStation {
    FireStationResponse personCoverageByFireStation(String station);
    List<String> getAdresses(String station);
    List<PersonDto> getPersonsDto(List<String> addresses);
    List<MedicalRecord> getBirthdays(List<PersonDto> persons);
    int getAdultCount(List<MedicalRecord> medicalRecords);
    boolean isOver18(String birthDate);
}
