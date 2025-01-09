package com.dly.safetynet.repositories;

import com.dly.safetynet.dto.PersonDto;
import com.dly.safetynet.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    //TODO creer un DTO
//    @Query("SELECT p.firstName, p.lastName, p.address, p.phone FROM Person p WHERE p.address = :address")
//    List<Person> findByAddress(@Param("address") String address);

    @Query("SELECT p FROM Person p WHERE p.address = :address")
    List<Person> findByAddress(@Param("address") String address);

}
