package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.dto.fire.FireDto;
import com.dly.safetynet.dto.fire.PersonFireDto;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.services.interfaces.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireService implements IFire {
    @Autowired
    private IPerson personService;
    @Autowired
    private IMedicalRecord recordService;
    @Autowired
    private IFireStation fireStationService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IChildAlert childAlertService;

    /**
     * http://localhost:8081/fire?address=<address>
     * Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi
     * que le numéro de la caserne de pompiers la desservant. La liste doit inclure :
     * - le nom,
     * - le numéro de téléphone
     * - l'âge
     * - les antécédents médicaux (médicaments, posologie et allergies)
     * de chaque personne.
     */
    @Override
    public Object getFireAddress(String address) {
        if (address.isEmpty() || address.isBlank()) throw new IllegalArgumentException("Address must not be empty");

        //Liste des personne vivant à l'adresse données
        List<PersonDto> personsDto = personService.findPersonByAddress(address);
        if (personsDto.isEmpty()) throw new IllegalArgumentException("No one lives at this address " + address);

        //Dossier médical des personnes
        List<MedicalRecord> records = recordService.findMedicalRecordByFirstNameAndLastName(personsDto);
        if (records.isEmpty()) throw new IllegalStateException("No medical records found");

        // List de person fire dto
        List<PersonFireDto> persons = getPersonsFireDto(records);
        if (persons.isEmpty()) throw new IllegalStateException("No persons found");

        // Numero de caserne deservit par l'adresse
        int stationNumber = getStationNumber(address);
        if (stationNumber == 0) throw new IllegalStateException("No fire station found");

        return new FireDto(address,persons,stationNumber);
    }

    private List<PersonFireDto> getPersonsFireDto(List<MedicalRecord> records) {
        return records.stream()
                .map(r -> {
                    PersonFireDto personFireDto = new PersonFireDto();
                    modelMapper.map(r, personFireDto);
                    personFireDto.setAge(childAlertService.calculateAge(r.getBirthdate()));
                    return personFireDto;
                }).collect(Collectors.toList());
    }

    private int getStationNumber(String address){
        return Integer.parseInt(fireStationService
                .findFireStationByAddress(address)
                .getStation());
    }


}
