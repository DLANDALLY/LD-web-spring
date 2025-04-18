package com.dly.safetynet.services;

import com.dly.safetynet.dto.personInfolastName.PersonInfoLastNameDto;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.entities.Person;
import com.dly.safetynet.services.interfaces.IChildAlert;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import com.dly.safetynet.services.interfaces.IPersonInfoLastName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonInfoLastNameService implements IPersonInfoLastName {
    @Autowired
    private IPerson personService;
    @Autowired
    private IMedicalRecord recordService;
    @Autowired
    private IChildAlert childAlertService;

    @Override
    public List<PersonInfoLastNameDto> getPersonInfoLastName(String lastName) {
        log.info("getPersonInfoLastName lastName {}", lastName);
        List<Person> persons = personService.findPersonsByLastName(lastName);

        List<MedicalRecord> records = recordService.findMedicalRecordByLastName(persons);

        return getPersonsInfoLastName(persons, records);
    }

    private List<PersonInfoLastNameDto> getPersonsInfoLastName(List<Person> persons,List<MedicalRecord> records){
        return persons.stream()
                .flatMap(p -> records.stream()
                        .filter(r ->
                                p.getLastName().equals(r.getLastName()) &&
                                        p.getFirstName().equals(r.getFirstName()))
                        .map(r -> {
                            PersonInfoLastNameDto personInfo = new PersonInfoLastNameDto();
                            personInfo.setLastName(r.getLastName());
                            personInfo.setAddress(p.getAddress());
                            personInfo.setAge(childAlertService.calculateAge(r.getBirthdate()));
                            personInfo.setEmail(p.getEmail());
                            personInfo.setMedications(r.getMedications());
                            personInfo.setAllergies(r.getAllergies());
                            return personInfo;
                        })
                ).distinct()
                .collect(Collectors.toList());
    }
}
