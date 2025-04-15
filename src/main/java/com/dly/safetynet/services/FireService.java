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
    private IChildAlert childAlertService;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public FireDto getFireAddress(String address) {
        if (address.isBlank()) throw new IllegalArgumentException("Address must not be empty");

        List<PersonDto> personsDto = personService.findPersonsDtoByAddress(address);
        if (personsDto.isEmpty()) throw new IllegalArgumentException("No one lives at this address " + address);

        List<MedicalRecord> records = recordService.findMedicalRecordByFirstNameAndLastName(personsDto);
        if (records.isEmpty()) throw new IllegalStateException("No medical records found");

        List<PersonFireDto> personFireDtos = getPersonsFireDto(records);
        if (personFireDtos.isEmpty()) throw new IllegalStateException("No persons found");

        int stationNumber = getStationNumber(address);
        if (stationNumber == 0) throw new IllegalStateException("No fire station found");

        return new FireDto(address, personFireDtos, stationNumber);
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
