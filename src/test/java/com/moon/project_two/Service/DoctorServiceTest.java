package com.moon.project_two.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.moon.project_two.DTO.DoctorDto;
import com.moon.project_two.DTO.DoctorResponseDto;
import com.moon.project_two.Entity.Doctor;
import com.moon.project_two.Repository.DoctorRepository;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    //mock repo layer
    private DoctorRepository doctorRepository;

    @Mock
    private ModelMapper modelMapper; 

    @InjectMocks
    //inject mocks to real service
    private DoctorService doctorService; 

    @BeforeEach
    void setUp(){
 
    }


    //You are verifying that the service: Calls the mapper correctly, Calls the repository correctly, Returns the correct response
    @Test
    public void testAddDoctor(){

            // Arrange
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setName("TestDoctor");
        doctorDto.setEmail("test@gmail.com");

        Doctor doctor = new Doctor();
        doctor.setName("TestDoctor");

        Doctor savedDoctor = new Doctor();
        savedDoctor.setId(1L);
        savedDoctor.setName("TestDoctor");

        DoctorResponseDto responseDto = new DoctorResponseDto();
        responseDto.setId(1L);
        responseDto.setName("TestDoctor");

        when(modelMapper.map(doctorDto, Doctor.class))
                .thenReturn(doctor);

        when(doctorRepository.save(doctor))
                .thenReturn(savedDoctor);

        when(modelMapper.map(savedDoctor, DoctorResponseDto.class))
                .thenReturn(responseDto);

        // Act
        DoctorResponseDto result = doctorService.addDoctor(doctorDto);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("TestDoctor", result.getName());

        verify(modelMapper).map(doctorDto, Doctor.class);
        verify(doctorRepository).save(doctor);
        verify(modelMapper).map(savedDoctor, DoctorResponseDto.class);

    }
     
    
}
