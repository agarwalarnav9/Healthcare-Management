package com.moon.project_two.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.moon.project_two.Entity.BloodGroupType;
import com.moon.project_two.Entity.Insurance;
import com.moon.project_two.Entity.Patient;



@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService; 

    @Autowired
    AppointmentService  appointmentService; 

    @Test
    public void addNewPatientTest(){
        Patient patient = Patient.builder()
                        .name("Arnav1")
                        .email("arnav@gmail.com")
                        .bloodGroup(BloodGroupType.O_POSITIVE)
                        .gender("male")
                        .birthDate(LocalDate.of(1991, 1, 1))
                        .build();
        Insurance insurance = new Insurance(null,"SBI", "1234",LocalDate.of(2028,12,12), null,null);
        
        // Patient patient2 = patientService.addNewPatient(patient);

        Patient patient2 = patientService.addNewPatientwithInsurance(patient, insurance);

        
        System.out.println(patient2);
    }


    // @Test
    // public void addInsuranceToExistingPatientTest(){

    //     Insurance insurance = new Insurance(null,"SBI", "1234",LocalDate.of(2028,12,12), null,null);

    //     patientService.addInsuranceToExistingPatient(4L, insurance);

    // }


    @Test
    public void findAllWithInsuranceAndAppointment(){

        // List<Patient> patients= patientService.findAllWithInsurance(); 
        // List<Patient> patients= patientService.findAllWithInsuranceAndAppointment(); 

        // for(Patient patient: patients){
        //     for(Appointment appointment : patient.getAppointments()){
        //         System.out.println(appointment);
        //     }   
        // }
    }

    // @Test
    // public void findallWithAppointment(){

    //     List<Patient> patients= patientService.findAllWithAppointment();
        
    // }

    @Test
    public void addNewAppointment(){
       appointmentService.addAppointment(2L,2L, LocalDateTime.of(2028, 12, 12, 14, 00, 00), "regular check up"); 
    }

    @Test
    public void deletePatient(){

        // Patient patients= patientService.deletePatient(1L); 
    }

    @Test
    public void deleteAppointment(){

        patientService.deleteAppointment(1L,4L); 
    }
}
