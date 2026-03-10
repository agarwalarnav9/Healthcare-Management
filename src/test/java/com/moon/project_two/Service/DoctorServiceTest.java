package com.moon.project_two.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.moon.project_two.DTO.DepartmentDto;
import com.moon.project_two.DTO.DoctorDto;



@SpringBootTest

public class DoctorServiceTest {
    
    @Autowired
    private DoctorService doctorService; 

    @Test
    public void testaddewDoctorandDepartment(){

        DoctorDto doctorDto = new DoctorDto(null, "Doctor1", "doctor1@gmail.com", "Cardio"); 
        DepartmentDto departmentDto = new DepartmentDto(null, "Department10", 1L); 

        doctorService.addNewDoctorandDept(doctorDto,departmentDto);

        doctorService.deleteDoctor(4L);
       

    }
}
