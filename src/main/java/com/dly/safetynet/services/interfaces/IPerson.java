package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.dto.childAlert.ChildAlertDto;
import com.dly.safetynet.entities.Person;

import java.util.List;
import java.util.Optional;

public interface IPerson {
    List<PersonDto> findByAddress(String address);
    ChildAlertDto getChildAlert(String address);
}
