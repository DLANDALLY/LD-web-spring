package com.dly.safetynet.services;

import com.dly.safetynet.services.interfaces.ICommunityEmail;
import com.dly.safetynet.services.interfaces.IPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommunityEmailService implements ICommunityEmail {
    @Autowired
    private IPerson personService;

    @Override
    public List<String> getCommunityEmailByCity(String city) {
        log.info("Getting community email");
        return personService.findEmailByCity(city);
    }
}
