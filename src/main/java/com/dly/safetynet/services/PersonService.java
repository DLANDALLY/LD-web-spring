package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.dto.PersonInfoDto;
import com.dly.safetynet.dto.childAlert.ChildAlertDto;
import com.dly.safetynet.dto.childAlert.ChildDto;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import com.dly.safetynet.services.interfaces.IPerson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService implements IPerson {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IMedicalRecord recordService;
    @Autowired
    private JsonDataService jsonData;


    @Override
    public List<PersonDto> findByAddress(String address) {
        return jsonData.getPersons()
                .stream()
                .filter(p -> p.getAddress().equals(address))
                .map(p -> modelMapper.map(p, PersonDto.class))
                .collect(Collectors.toList());
    }

    /**
     * http://localhost:8081/childAlert?address=<address>
     * Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
     * La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
     * membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.
     */
    @Override
    public ChildAlertDto getChildAlert(String address){
        // Retourn list d'habitant a cette adresse
        List<PersonDto> persons = findByAddress(address);
        persons.forEach(System.out::println);

//        Collection<PersonInfoDto> personsInfos = persons.stream().map(p -> {
//            Collection<PersonInfoDto> pi = findPersonByFirstNameAndLastName(p.getLastName());
//            return modelMapper.map(pi, PersonInfoDto.class);
//        }).collect(Collectors.toList());
//        personsInfos.forEach(System.out::println);

        // List medicalrecords par nom de famille
        List<MedicalRecord> medicalrecords = recordService.findBirthdayByFirstNameAndLastName(persons);
        medicalrecords.forEach(System.out::println);

        List<ChildDto> childDto = getChildrenDto(medicalrecords);

        return new ChildAlertDto(childDto);
    }

    private int age(String birthDay){
        LocalDate dateOfBirth = LocalDate.parse(birthDay, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate now = LocalDate.now();
        return now.getYear() - dateOfBirth.getYear();
    }


    public List<ChildDto> getChildrenDto(List<MedicalRecord> medicalrecords ) {
        return medicalrecords.stream()
                .map(m -> {
                    ChildDto childDto = modelMapper.map(m, ChildDto.class);
                    childDto.setAge(age(m.getBirthdate()));
                    return childDto;
                }).toList();
    }

    public Collection<PersonInfoDto> findPersonByFirstNameAndLastName(String lastname) {
        //Collection<PersonInfoDto> personInfos = personInfoRepository.findByLastName(lastname);
        return null;
    }

}
