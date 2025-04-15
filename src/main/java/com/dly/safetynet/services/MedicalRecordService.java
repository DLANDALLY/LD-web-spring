package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.form.MedicalRecordForm;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import com.dly.safetynet.services.utils.MedicalRecordUtils;
import com.dly.safetynet.services.utils.PersonUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecord {
    @Autowired
    private JsonDataService jsonData;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IPerson personService;

    public List<MedicalRecord> findAllMedicalRecords(){
        return jsonData.getMedicalRecords();
    }

    @Override
    public List<MedicalRecord> findMedicalRecordByFirstNameAndLastName(List<PersonDto> persons) {
         return persons.stream()
                 .map(person -> findAllMedicalRecords().stream()
                         .filter(record -> record.getFirstName().equals(person.getFirstName()) &&
                                 record.getLastName().equals(person.getLastName()))
                         .findFirst()
                         .orElse(null))
                 .filter(record -> record != null && !record.getBirthdate().isEmpty())
                 .toList();
    }

    @Override
    public List<MedicalRecord> findMedicalRecordByLastName(List<Person> persons) {
        return persons.stream()
                .map(person -> findAllMedicalRecords().stream()
                        .filter(record -> record.getFirstName().equals(person.getFirstName()) &&
                                record.getLastName().equals(person.getLastName()))
                        .findFirst()
                        .orElse(null))
                .filter(record -> record != null && !record.getBirthdate().isEmpty())
                .toList();
    }

    @Override
    public void creatMedicalRecord(MedicalRecordForm medicalRecordForm) throws IOException {
        List<MedicalRecord> medicalRecords = findAllMedicalRecords();
        List<Person> persons = personService.findAllPersons();
        MedicalRecord medicalRecord = medicalRecordMapper(medicalRecordForm);

        boolean personFound = PersonUtils.checkPersonExists(medicalRecord, persons);
        if (!personFound) throw new IllegalArgumentException("Creat a Person before creat his medical record");

        boolean medicalRecordExist = MedicalRecordUtils.checkMedicalRecordExists(medicalRecord, medicalRecords);
        if (medicalRecordExist) throw new IllegalArgumentException("medicalRecord already exists");

        medicalRecords.add(medicalRecord);
        jsonData.writeDataToJson(medicalRecord);
    }

    @Override
    public void updateMedicalRecord(MedicalRecordForm medicalRecordForm) throws IOException {
        List<MedicalRecord> medicalRecords = findAllMedicalRecords();
        MedicalRecord medicalRecord = medicalRecordMapper(medicalRecordForm);

        MedicalRecord foundMedicalRecord = MedicalRecordUtils.findExactMedicalRecord(medicalRecord, medicalRecords);
        boolean sameMedicalRecord = MedicalRecordUtils.sameMedicalRecord(medicalRecord, medicalRecords);

        if (foundMedicalRecord == null) throw new IllegalArgumentException("MedicalRecord not found");
        if (sameMedicalRecord) throw new IllegalArgumentException("Oops, no changes received !");

        for (int i = 0; i < medicalRecords.size(); i++) {
            if (medicalRecords.get(i).equals(foundMedicalRecord))
                medicalRecords.set(i, medicalRecord);
        }
        jsonData.updateDataToJson(medicalRecords);
    }

    @Override
    public String deleteMedicalRecord(MedicalRecord medicalRecord) throws IOException {
        List<MedicalRecord> medicalRecords = findAllMedicalRecords();
        MedicalRecord foundMedicalRecord = MedicalRecordUtils.findExactMedicalRecord(medicalRecord, medicalRecords);
        if (foundMedicalRecord == null) throw new IllegalArgumentException("MedicalRecord not found");

        medicalRecords.removeIf(m -> m.equals(foundMedicalRecord));
        jsonData.updateDataToJson(medicalRecords);
        return "The medical record has been successfully deleted";
    }

    private MedicalRecord medicalRecordMapper(MedicalRecordForm medicalRecordForm) {
        return  modelMapper.map(medicalRecordForm, MedicalRecord.class);
    }
}
