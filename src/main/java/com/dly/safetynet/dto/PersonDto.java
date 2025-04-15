package com.dly.safetynet.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonDto {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public PersonDto() {
    }

    public PersonDto(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonDto{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
