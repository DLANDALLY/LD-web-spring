package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.dto.floodStation.FloodStationDto;
import com.dly.safetynet.dto.floodStation.HouseholdMember;
import com.dly.safetynet.dto.floodStation.HouseholdServedDto;
import com.dly.safetynet.entities.MedicalRecord;
import com.dly.safetynet.services.interfaces.IChildAlert;
import com.dly.safetynet.services.interfaces.IFireStation;
import com.dly.safetynet.services.interfaces.IFlood;
import com.dly.safetynet.services.interfaces.IMedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FloodService implements IFlood {
    @Autowired
    private IFireStation fireStationService;
    @Autowired
    private IMedicalRecord recordsService;
    @Autowired
    private IChildAlert childAlertService;

    /**
     * http://localhost:8081/flood/stations?stations=<a list of station_numbers>
     * Cette url doit retourner :
     * - une liste de tous les foyers desservis par la caserne
     *      - Cette liste doit regrouper les personnes par adresse
     *      - Elle doit aussi inclure :
     *          - le nom
     *          - le numéro de téléphone
     *          - l'âge des habitants
     *          - faire figurer leurs antécédents médicaux (médicaments, posologie et allergies)
     *              à côté de chaque nom
     */
    @Override
    public FloodStationDto getFloodStations(List<String> stationNumbers) {
        // TODO Ajout des exceptions
        Set<String> address = getAdresses(stationNumbers);

        List<PersonDto> persons = fireStationService.getPersonsDto(address);

        List<MedicalRecord> records = recordsService.findMedicalRecordByFirstNameAndLastName(persons);

        List<HouseholdMember> members = getHouseholdMembers(persons, records);

        List<HouseholdServedDto> householdServedDtos = getHouseholdServedDtos(address, members);

        return new FloodStationDto(householdServedDtos);
    }

    private Set<String> getAdresses(List<String> stationNumbers){
        return stationNumbers.stream()
                .map(s -> fireStationService.findAddress(s))
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    private List<HouseholdMember> getHouseholdMembers(List<PersonDto> persons, List<MedicalRecord> records) {
        return persons.stream()
                .map(p -> {
                    HouseholdMember member = new HouseholdMember();
                    for ( MedicalRecord record : records){
                        if (record.getFirstName().equals(p.getFirstName())
                                && record.getLastName().equals(p.getLastName())){
                            member.setFullName(p.getFirstName() +" "+ record.getLastName());
                            member.setPhone(p.getPhone());
                            member.setAge(childAlertService.calculateAge(record.getBirthdate()));
                            member.setMedications(record.getMedications());
                            member.setAllergies(record.getAllergies());
                            member.setAddress(p.getAddress());
                        }
                    }
                    return member;
                }).collect(Collectors.toList());
    }

    private List<HouseholdServedDto> getHouseholdServedDtos(Set<String> address, List<HouseholdMember> members) {
        return address.stream()
                .map(a -> {
                    HouseholdServedDto householdServedDto = new HouseholdServedDto();
                    householdServedDto.setAddress(a);
                    householdServedDto.setHouseHolds(members.stream()
                           .filter(m -> m.getAddress().equals(a))
                           .collect(Collectors.toList()));
                    return householdServedDto;
                }).collect(Collectors.toList());
    }
}
