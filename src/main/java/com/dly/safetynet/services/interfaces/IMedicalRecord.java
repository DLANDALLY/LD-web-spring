package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.MedicalRecord;

import java.util.List;

public interface IMedicalRecord {
    List<MedicalRecord> findMedicalRecordByFirstNameAndLastName(List<PersonDto> persons);
}