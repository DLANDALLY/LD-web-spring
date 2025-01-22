package com.dly.safetynet.services;

import com.dly.safetynet.services.interfaces.ICommunityEmail;
import com.dly.safetynet.services.interfaces.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityEmailService implements ICommunityEmail {
    @Autowired
    private IPerson personService;

    @Override
    public List<String> getCommunityEmailByCity(String city) {
        return personService.findEmailByCity(city);
    }
}
