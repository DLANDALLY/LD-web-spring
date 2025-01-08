package com.dly.safetynet.repositories;

import com.dly.safetynet.entities.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FireStationRepository extends JpaRepository<FireStation, Long> {
}
