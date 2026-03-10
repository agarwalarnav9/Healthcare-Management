package com.moon.project_two.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moon.project_two.Entity.Appointment;
import com.moon.project_two.Entity.Insurance;
import com.moon.project_two.Entity.Patient;

import com.moon.project_two.Repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {
    
   
    private final PatientRepository patientRepository;

    @Transactional
    public Patient addNewPatient(Patient patientDto){
        Patient patient = patientRepository.save(patientDto);
        return patient;
    }

    @Transactional
    public Patient addNewPatientwithInsurance(Patient patientDto, Insurance insurance){
        
        patientDto.setInsurance(insurance);
        insurance.setPatient(patientDto);

        return patientRepository.save(patientDto);
        
    }

    @Transactional
    public Patient addInsuranceToExistingPatient(Long id, Insurance insurance){
        
        //runs immediately, put patient to persistant context
        Patient patient = patientRepository.findById(id).orElseThrow();
        
        //insurance is in transient context
        insurance.setPatient(patient);
        
        //patient is dirtied 
        patient.setInsurance(insurance); 
        
        
        //at transaction commit, patient is dirtied, cascading detection triggers, saves insurance

        return patient;
    }

    public List<Patient> findall() {
        
       List<Patient> patients =  patientRepository.findAll();
        
       return patients;
        
    }

    @Transactional
    public List<Patient> findAllWithInsurance() {
        
       List<Patient> patients =  patientRepository.findAllWithInsurance();
       
       for(Patient patient : patients){
            List<Appointment> appointments = patient.getAppointments(); 
            System.out.println(appointments);
       }

       return patients;
        
    }


    public List<Patient> findAllWithInsuranceAndAppointment() {
        return patientRepository.findAllWithInsuranceAndAppointment(); 
    }

    @Transactional
    public Patient deletePatient(Long id){

        Patient patient = patientRepository.findById(id).orElseThrow();

        patientRepository.deleteById(id); 

        return patient; 

    }

    @Transactional
    public void deleteAppointment(Long p_id, Long a_id){

        Patient patient = patientRepository.findById(p_id).orElseThrow();

        List<Appointment> appointments = patient.getAppointments(); 
        
        for(Appointment appointment : appointments){
            appointment.setPatient(null);
        }

        patientRepository.save(patient);

    }



}
