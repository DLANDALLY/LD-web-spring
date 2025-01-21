package com.dly.safetynet.services;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.services.interfaces.IPerson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService implements IPerson {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JsonDataService jsonData;


    @Override
    public List<PersonDto> findPersonByAddress(String address) {
        return jsonData.getPersons()
                .stream()
                .filter(p -> p.getAddress().equals(address))
                .map(p -> modelMapper.map(p, PersonDto.class))
                .collect(Collectors.toList());
    }

}
