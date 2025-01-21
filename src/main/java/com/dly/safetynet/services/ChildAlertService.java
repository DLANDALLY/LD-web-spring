package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.dto.childAlert.ChildAlertDto;
import com.dly.safetynet.dto.childAlert.ChildDto;
import com.dly.safetynet.dto.childAlert.FamilyMembersDto;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.services.interfaces.IChildAlert;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildAlertService implements IChildAlert {
    @Autowired
    private IPerson personService;
    @Autowired
    private IMedicalRecord recordService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * http://localhost:8081/childAlert?address=892 Downing Ct
     * Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
     * La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
     * membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.
     */
    @Override
    public ChildAlertDto getChildAlert(String address){
        if (address == null || address.isBlank()) throw new IllegalArgumentException("Address cannot be blank");

        List<PersonDto> persons = personService.findByAddress(address);
        if (persons.isEmpty()) throw new IllegalArgumentException("No one lives at this address " + address);

        //Récupérer les enfants de cette adresse
        List<MedicalRecord> medicalrecords = recordService.findMedicalRecordByFirstNameAndLastName(persons);
        if (medicalrecords.isEmpty()) throw new IllegalArgumentException("No children living at this address");

        //Liste de persons avec leur ages
        List<ChildDto> agePerson = getAgePerson(medicalrecords);
        if (agePerson.isEmpty()) throw new IllegalArgumentException("Dates of birth are not given");

        //Filtrer les enfants de 18 ans ou moins
        List<ChildDto> children = getChildDtoList(agePerson);
        if (children.isEmpty()) throw new IllegalArgumentException("No children under 18 years old");

        //Recherche membre de la famille
        List<ChildDto> household = getHousehold(children, agePerson);
        if (household.isEmpty()) throw new IllegalArgumentException("No household members under 18 years old");

        return new ChildAlertDto(household);
    }


    @Override
    public List<ChildDto> getAgePerson(List<MedicalRecord> medicalrecords) {
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
                .map(child -> {
                    List<FamilyMembersDto> families = agePerson.stream()
                            .filter(person -> person.getLastName().equals(child.getLastName()))
                            .map(p -> modelMapper.map(p, FamilyMembersDto.class))
                            .collect(Collectors.toList());
                    families.removeIf(f -> f.getFirstName().equals(child.getFirstName()) &&
                            f.getLastName().equals(child.getLastName()) &&
                            f.getAge() == child.getAge());
                    child.setFamilyMembers(families);
                    return child;
                }).collect(Collectors.toList());
    }



}
