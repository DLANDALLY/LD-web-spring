package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.dto.phoneAlert.PhoneAlertDto;
import com.dly.safetynet.entities.FireStation;
import com.dly.safetynet.services.interfaces.IFireStation;
import com.dly.safetynet.services.interfaces.IPerson;
import com.dly.safetynet.services.interfaces.IPhoneAlert;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneAlertService implements IPhoneAlert {
    @Autowired
    private IPerson personService;
    @Autowired
    private IFireStation fireStationService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PhoneAlertDto> getPhoneAlert(int firestationNumber){
        if(firestationNumber < 1 || firestationNumber > fireStationService.findAllFireStations().size())
            throw new IllegalArgumentException("Firestation number should be between 1 and " + fireStationService.findAllFireStations().size());

        List<FireStation> fireStations = addId(fireStationService.findAllFireStations());
        if(fireStations.isEmpty()) throw new IllegalStateException("The fire station list is empty");

        FireStation fireStation = getFireStation(fireStations, firestationNumber);
        if(fireStation == null) throw new IllegalStateException("The fire station is not found");

        List<PersonDto> personDtos = personService.findPersonsDtoByAddress(fireStation.getAddress());
        if(personDtos.isEmpty()) throw new IllegalStateException("No residents are covered by this station number");

        List<PhoneAlertDto> phonesAlert = getPhoneAlert(personDtos);
        if (phonesAlert.isEmpty()) throw new IllegalStateException(" No phones are covered by this station number");

        return phonesAlert;
    }

    private List<FireStation>  addId(List<FireStation> fireStations){
        for (int i = 0; i < fireStations.size(); i++){
            fireStations.get(i).setId(i+1L);
        }
        return fireStations;
    }

    private FireStation getFireStation(List<FireStation> fireStations, int firestationNumber){
        return fireStations.stream()
                .filter(f -> f.getId().equals((long)firestationNumber))
                .findFirst().orElseThrow();
    }

    private List<PhoneAlertDto> getPhoneAlert(List<PersonDto> personDtos){
        return personDtos.stream()
                .map(p -> modelMapper.map(p, PhoneAlertDto.class))
                .collect(Collectors.toList());
    }
}
