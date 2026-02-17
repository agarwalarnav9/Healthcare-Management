package com.moon.project_two.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moon.project_two.Entity.Insurance;
import com.moon.project_two.Entity.Patient;
import com.moon.project_two.Repository.InsuranceRepository;
import com.moon.project_two.Repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository; 

    @Transactional
    public void addInsurance(Insurance insurance, Long id){
        Patient patient = patientRepository.findById(id).orElseThrow();
        insurance.setPatient(patient);
        insuranceRepository.save(insurance);
        
    }

}
