package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;

import java.util.List;

public interface IDataJsonService {

    List<Person> getPersons();

    List<FireStation> getFireStations();

    List<MedicalRecord> getMedicalRecords();
}
