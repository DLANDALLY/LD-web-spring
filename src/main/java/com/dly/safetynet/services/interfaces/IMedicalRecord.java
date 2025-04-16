package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.form.MedicalRecordForm;

import java.util.List;

public interface IMedicalRecord {

    List<MedicalRecord> findMedicalRecordByFirstNameAndLastName(List<PersonDto> persons);

    List<MedicalRecord> findMedicalRecordByLastName(List<Person> persons);

    void creatMedicalRecord(MedicalRecordForm medicalRecordForm);

    void updateMedicalRecord(MedicalRecordForm medicalRecordForm);

    String deleteMedicalRecord(MedicalRecord medicalRecord);
}