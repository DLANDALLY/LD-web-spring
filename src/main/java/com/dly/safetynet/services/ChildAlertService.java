package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.dto.childAlert.ChildAlertDto;
import com.dly.safetynet.dto.childAlert.ChildDto;
import com.dly.safetynet.dto.childAlert.FamilyMembersDto;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.services.interfaces.IChildAlert;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ChildAlertService implements IChildAlert {
    @Autowired
    private IPerson personService;
    @Autowired
    private IMedicalRecord recordService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ChildAlertDto getChildAlert(String address){
        log.info("Getting child alert");
        if (address.isBlank()) throw new IllegalArgumentException("Address cannot be blank");

        List<PersonDto> persons = personService.findPersonsDtoByAddress(address);
        if (persons.isEmpty()) throw new NoSuchElementException("No one lives at this address " + address);

        //Récupérer les enfants de cette adresse
        List<MedicalRecord> medicalrecords = recordService.findMedicalRecordByFirstNameAndLastName(persons);
        if (medicalrecords.isEmpty()) throw new NoSuchElementException("No children living at this address");

        //Liste de persons avec leur ages
        List<ChildDto> agePerson = getAgePerson(medicalrecords);
        if (agePerson.isEmpty()) throw new IllegalArgumentException("Dates of birth are not given");

        //Filtrer les enfants de 18 ans ou moins
        List<ChildDto> children = getChildDtoList(agePerson);
        if (children.isEmpty()) throw new NoSuchElementException("No children under 18 years old");

        //Recherche membre de la famille
        List<ChildDto> household = getHousehold(children, agePerson);
        if (household.isEmpty()) throw new NoSuchElementException("No household members under 18 years old");

        return new ChildAlertDto(household);
    }

    private List<ChildDto> getAgePerson(List<MedicalRecord> medicalrecords) {
        return medicalrecords.stream()
                .map(m -> {
                    ChildDto childDto = modelMapper.map(m, ChildDto.class);
                    childDto.setAge(calculateAge(m.getBirthdate()));
                    return childDto;
                }).toList();
    }

    @Override
    public int calculateAge(String birthDay){
        LocalDate dateOfBirth = LocalDate.parse(birthDay, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate now = LocalDate.now();
        return now.getYear() - dateOfBirth.getYear();
    }

    private List<ChildDto> getChildDtoList(List<ChildDto> agePerson) {
        return agePerson.stream()
                .filter(a -> a.getAge() <= 18)
                .collect(Collectors.toList());
    }

    private List<ChildDto> getHousehold(List<ChildDto> children, List<ChildDto> agePerson){
        return children.stream()
                .peek(child -> {
                    List<FamilyMembersDto> families = agePerson.stream()
                            .filter(person -> person.getLastName().equals(child.getLastName()))
                            .map(p -> modelMapper.map(p, FamilyMembersDto.class))
                            .collect(Collectors.toList());
                    families.removeIf(f -> f.getFirstName().equals(child.getFirstName()) &&
                            f.getLastName().equals(child.getLastName()) &&
                            f.getAge() == child.getAge());
                    child.setFamilyMembers(families);
                }).collect(Collectors.toList());
    }
}
