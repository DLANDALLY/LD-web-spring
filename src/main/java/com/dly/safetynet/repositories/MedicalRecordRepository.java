package com.dly.safetynet.repositories;

import com.dly.safetynet.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long>{
//    @Query("SELECT m.birthdate FROM MedicalRecord m WHERE m.firstname = :firstname AND m.lastname = :lastname")
//    MedicalRecord findBirthdayByFirstnameAndLastname(@Param("firstname") String firstName, @Param("lastname") String lastName);

    @Query("SELECT m FROM MedicalRecord m WHERE m.firstName = :firstname AND m.lastName = :lastname")
    MedicalRecord findBirthdayByFirstnameAndLastname(@Param("firstname") String firstName, @Param("lastname") String lastName);
}
