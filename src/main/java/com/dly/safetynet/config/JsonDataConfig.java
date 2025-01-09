package com.dly.safetynet.config;

import com.dly.safetynet.services.JsonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class JsonDataConfig implements CommandLineRunner {
    @Autowired
    private JsonDataService jsonDataService;

    @Override
    public void run(String... args) throws Exception {
        this.jsonDataService.loadDataFromJson();
    }
}
