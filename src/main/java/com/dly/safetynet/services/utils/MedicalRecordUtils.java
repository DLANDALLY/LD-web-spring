package com.dly.safetynet.services.utils;

import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;

import java.util.List;

public class MedicalRecordUtils {

    public static boolean checkMedicalRecordExists(MedicalRecord medicalRecord, List<MedicalRecord> allMedicalRecords) {
        return allMedicalRecords.stream()
                .anyMatch(m ->
                    m.getFirstName().equals(medicalRecord.getFirstName()) &&
                    m.getLastName().equals(medicalRecord.getLastName()) &&
                    m.getBirthdate().equals(medicalRecord.getBirthdate()));
    }

    //TODO: Meme resultat
    public static boolean sameMedicalRecord(MedicalRecord medicalRecord, List<MedicalRecord> allMedicalRecords) {
        return allMedicalRecords.stream()
                .anyMatch(m ->
                    m.getFirstName().equals(medicalRecord.getFirstName()) &&
                    m.getLastName().equals(medicalRecord.getLastName()) &&
                    m.getBirthdate().equals(medicalRecord.getBirthdate()) &&
                    m.getAllergies().equals(medicalRecord.getAllergies()) &&
                    m.getMedications().equals(medicalRecord.getMedications())
                );
    }

    public static MedicalRecord findExactMedicalRecord(MedicalRecord medicalRecord, List<MedicalRecord> allMedicalRecords) {
        return allMedicalRecords.stream()
                .filter(m ->
                    m.getFirstName().equals(medicalRecord.getFirstName()) &&
                    m.getLastName().equals(medicalRecord.getLastName()) &&
                    m.getBirthdate().equals(medicalRecord.getBirthdate()))
                .findFirst().orElse(null);
    }
}
