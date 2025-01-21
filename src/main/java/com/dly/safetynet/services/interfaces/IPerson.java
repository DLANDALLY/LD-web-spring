package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.PersonDto;

import java.util.List;

public interface IPerson {
    List<PersonDto> findPersonByAddress(String address);
}
