package com.dly.safetynet.dto.phoneAlert;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PhoneAlertDto {
    private String phone;

    public PhoneAlertDto() {
    }

    public PhoneAlertDto(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PhoneAlertDto{");
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
