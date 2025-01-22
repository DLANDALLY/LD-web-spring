package com.dly.safetynet.services.interfaces;

import com.dly.safetynet.dto.fire.FireDto;

public interface IFire {
    FireDto getFireAddress(String address);
}
