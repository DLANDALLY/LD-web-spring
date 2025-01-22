package com.dly.safetynet.services.interfaces;

import java.util.List;

public interface ICommunityEmail {
    List<String> getCommunityEmailByCity(String city);
}
