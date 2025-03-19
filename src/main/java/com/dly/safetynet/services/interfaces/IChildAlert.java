package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.childAlert.ChildAlertDto;
import com.dly.safetynet.dto.childAlert.ChildDto;
import com.dly.safetynet.entities.MedicalRecord;

import java.util.List;

public interface IChildAlert {
    ChildAlertDto getChildAlert(String address);

    //List<ChildDto> getAgePerson(List<MedicalRecord> medicalrecords);

    int calculateAge(String birthDay);
}
