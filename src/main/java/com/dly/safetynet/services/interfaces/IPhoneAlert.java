package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.phoneAlert.PhoneAlertDto;

import java.util.List;

public interface IPhoneAlert {
    List<PhoneAlertDto> getPhoneAlert(int firestationNumber);
}
