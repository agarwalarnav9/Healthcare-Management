package com.moon.project_two.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.moon.project_two.Entity.Appointment;
import com.moon.project_two.Entity.Doctor;
import com.moon.project_two.Entity.Patient;
import com.moon.project_two.Repository.AppointmentRepository;
import com.moon.project_two.Repository.DoctorRepository;
import com.moon.project_two.Repository.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class AppointmentService {
    
    private final AppointmentRepository appointmentRepository; 
    private final PatientRepository patientRepository; 
    private final DoctorRepository doctorRepository; 

    @Transactional
    public Appointment addAppointment(Long doctor_id, Long patient_id, LocalDateTime appointment_time, String reason){

        Patient patient = patientRepository.findById(patient_id).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow();
        
        Appointment appointment = new Appointment(null, appointment_time, reason, patient, doctor);

        return appointmentRepository.save(appointment);  
    }



}
