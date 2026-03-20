package com.moon.project_two.ServiceHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.moon.project_two.Entity.BloodGroupType;
import com.moon.project_two.Entity.Insurance;
import com.moon.project_two.Entity.Patient;
import com.moon.project_two.Service.AppointmentService;
import com.moon.project_two.Service.PatientService;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService; 

    @Autowired
    AppointmentService  appointmentService; 

    @Test
    public void addNewPatientTest(){
        Patient patient = new Patient();
        patient.setName("Arnav1");
        patient.setEmail("arnav@gmail.com");
        patient.setBloodGroup(BloodGroupType.O_POSITIVE);
        patient.setGender("male");
        patient.setBirthDate(LocalDate.of(1991, 1, 1));

        Insurance insurance = new Insurance();
        insurance.setProvider("SBI");
        insurance.setPolicyNumber("1234");
        insurance.setValidity(LocalDate.of(2028,12,12));
        
        // Patient patient2 = patientService.addNewPatient(patient);

        Patient patient2 = patientService.addNewPatientwithInsurance(patient, insurance);

        
        System.out.println(patient2);
    }


    // @Test
    // public void addInsuranceToExistingPatientTest(){

    //     Insurance insurance = new Insurance(null,"SBI", "1234",LocalDate.of(2028,12,12), null,null);

    //     patientService.addInsuranceToExistingPatient(4L, insurance);
    //
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
