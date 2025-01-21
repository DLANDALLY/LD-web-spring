package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecord {
    @Autowired
    private JsonDataService jsonData;
    @Override
    public List<MedicalRecord> findMedicalRecordByFirstNameAndLastName(List<PersonDto> persons) {
         return persons.stream()
                 .map(person -> jsonData.getMedicalRecords().stream()
                         .filter(record -> record.getFirstName().equals(person.getFirstName()) &&
                                 record.getLastName().equals(person.getLastName()))
                         .findFirst()
                         .orElse(null))
                 .filter(record -> record != null && !record.getBirthdate().isEmpty())
                 .toList();
    }
}
