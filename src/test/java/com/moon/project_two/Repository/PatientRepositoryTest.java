package com.moon.project_two.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.moon.project_two.Entity.BloodGroupType;
import com.moon.project_two.Entity.Insurance;
import com.moon.project_two.Entity.Patient;

@DataJpaTest
public class PatientRepositoryTest {
    
    @Autowired
    private PatientRepository patientRepository; 

    private Patient savedPatient;
    private Insurance insurance;

    @BeforeEach
    public void setup()
    {   
        savedPatient = new Patient();
        savedPatient.setName("Arnav");
        savedPatient.setEmail("arnav@gmail.com");
        savedPatient.setBloodGroup(BloodGroupType.O_POSITIVE);
        savedPatient.setGender("male");
        savedPatient.setBirthDate(LocalDate.of(1991, 1, 1));

        insurance = new Insurance();
        insurance.setProvider("SBI");
        insurance.setPolicyNumber("1234");
        insurance.setValidity(LocalDate.of(2028,12,12));

        savedPatient.setInsurance(insurance);
        insurance.setPatient(savedPatient);

        savedPatient = patientRepository.save(savedPatient);
    }
    @AfterEach
    void printDatabaseState() {
        System.out.println("---- DATABASE STATE ----");
        patientRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void testfindByName(){
        Patient patient = patientRepository.findByName("Arnav").orElseThrow();

        assertEquals(patient.getId(), savedPatient.getId());
        assertTrue(patientRepository.findByName("arnav").isEmpty());
        
    }
    
    @Test
    public void testfindAllWithInsurance(){

        List<Patient> patients = patientRepository.findAllWithInsurance();
        
        assertEquals(1, patients.size());
        assertEquals(patients.get(0).getInsurance().getId(), insurance.getId());      

    }

}
