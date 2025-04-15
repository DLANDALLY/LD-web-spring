package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.childAlert.ChildAlertDto;

public interface IChildAlert {
    ChildAlertDto getChildAlert(String address);

    int calculateAge(String birthDay);
}
