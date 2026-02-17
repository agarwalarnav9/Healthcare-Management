package com.moon.project_two.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moon.project_two.Entity.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

   @Query("SELECT p from Patient p LEFT JOIN FETCH p.insurance") 
   List<Patient> findAllWithInsurance(); 

   @Query("SELECT DISTINCT p from Patient p LEFT JOIN FETCH p.insurance LEFT JOIN FETCH p.appointments") 
   List<Patient> findAllWithInsuranceAndAppointment(); 
   
   
}
