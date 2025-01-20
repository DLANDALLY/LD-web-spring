package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;

import java.util.List;

public interface IMedicalRecord {
    List<MedicalRecord> findBirthdayByFirstNameAndLastName(List<PersonDto> persons);
}