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

    /**
     * http://localhost:8080/communityEmail?city=<city>
     * Cette url doit retourner :
     * - les adresses mail de tous les habitants de la ville.
     */
    @Override
    public List<String> getCommunityEmailByCity(String city) {
        System.out.println("## city " + city);
        List<String> emails = personService.findEmailByCity(city);
        emails.forEach(System.out::println);

        return emails;
    }
}
