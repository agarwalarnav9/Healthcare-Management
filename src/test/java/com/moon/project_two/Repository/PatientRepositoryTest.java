package com.moon.project_two.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.moon.project_two.Entity.BloodGroupType;
import com.moon.project_two.Entity.Patient;

@DataJpaTest
public class PatientRepositoryTest {
    
    @Autowired
    private PatientRepository patientRepository; 

    Patient savedpatient;

    @BeforeEach
    public void before()
    {   
        savedpatient = Patient.builder()
                            .name("Arnav")
                            .email("arnav@gmail.com")
                            .bloodGroup(BloodGroupType.B_NEGATIVE)
                            .birthDate(LocalDate.of(1991, 1, 9))
                            .gender("male")
                            .build();

        patientRepository.save(savedpatient);
    }

    @AfterEach
    public void after(){
        patientRepository.deleteAll();
    }

    @Test
    public void testfindByName(){
        Patient patient = patientRepository.findByName("Arnav").orElseThrow();

        assertEquals(patient.getEmail(), savedpatient.getEmail());
        
    }
    
}
