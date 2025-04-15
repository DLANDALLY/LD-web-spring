package com.dly.safetynet.services.utils;

import com.dly.safetynet.entities.FireStation;

import java.util.List;

public class FireStationUtils {

    public static boolean checkFireStationExists(FireStation fireStation, List<FireStation> allFireStations) {
        return allFireStations.stream()
                .anyMatch(f ->
                    f.getAddress().equals(fireStation.getAddress()) &&
                    f.getStation().equals(fireStation.getStation()));
    }

    public static boolean sameFireStation(FireStation fireStation, List<FireStation> allFireStations) {
        return allFireStations.stream()
                .anyMatch(f ->
                    f.getAddress().equals(fireStation.getAddress()) &&
                    f.getStation().equals(fireStation.getStation()));
    }

    public static FireStation findExactFireStation(FireStation fireStation, List<FireStation> allFireStations) {
        return allFireStations.stream()
                .filter(f ->
                    f.getAddress().equals(fireStation.getAddress()))
                .findFirst().orElse(null);
    }
}
