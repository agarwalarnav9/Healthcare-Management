package com.moon.project_two.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moon.project_two.DTO.DoctorDto;
import com.moon.project_two.DTO.DoctorResponseDto;
import com.moon.project_two.DTO.ExtrnalApiResponse.LabReportResponse;
import com.moon.project_two.Service.DepartmentService;
import com.moon.project_two.Service.DoctorService;
import com.moon.project_two.Service.ExternalService;


@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    
    @Autowired
    private MockMvc mockMvc; 
    
    //used when testing class in SpringBoot Application Context
    //can even mock concrete classes
    @MockitoBean
    private DoctorService doctorService;  
    
    @MockitoBean
    private DepartmentService departmentService;
  
    @MockitoBean
    private ExternalService externalService;

    @Autowired
    private ObjectMapper objectMapper; 

    private DoctorResponseDto doctorResponseDto;
    private LabReportResponse labReportResponse; 

    @BeforeEach
    void setUp(){
        doctorResponseDto = new DoctorResponseDto(); 
        doctorResponseDto.setId(1L);
        doctorResponseDto.setName("TestName");
        doctorResponseDto.setEmail("testname@gmail.com");

        labReportResponse = new LabReportResponse(); 
        labReportResponse.setLabLocation("Jaipur");
        labReportResponse.setStatus("Processed");
        labReportResponse.setTestReport("You are healthy");
        labReportResponse.setCreatedAt(LocalDateTime.now());

    }

    @Test
    //request JSON is accepted, service method is called, correct HTTP response is returned
    public void testcreateDoctor() throws Exception{
        DoctorDto doctorDto = new DoctorDto(); 
        doctorDto.setName("TestName");
        doctorDto.setEmail("testname@gmail.com");
        doctorDto.setSpecialization("Cardiology");

        when(doctorService.addDoctor(any(DoctorDto.class)))
            .thenReturn(doctorResponseDto);

        this.mockMvc.perform(post("/admins/doctors")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(doctorDto)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name").value("TestName"));


    }

    @Test
    public void testgetReportforPatientByNameAndEmail() throws Exception{

        String name = "test";
        String email = "test@gmail.com";

        when(externalService.getReportforPatientByNameAndEmail(anyString(), anyString()))
            .thenReturn(labReportResponse);

        this.mockMvc.perform(get("/admins/reports/{name}/{email}", name, email))
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.labLocation").value(labReportResponse.getLabLocation()));

    }

}
