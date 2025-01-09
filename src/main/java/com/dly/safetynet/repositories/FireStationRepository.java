package com.dly.safetynet.repositories;

import com.dly.safetynet.entities.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireStationRepository extends JpaRepository<FireStation, Long> {
    @Query("SELECT f FROM FireStation f WHERE f.station = :station")
    List<FireStation> findByStation(@Param("station") String station);
}
