package com.moon.project_two.Service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.moon.project_two.DTO.DepartmentDto;
import com.moon.project_two.DTO.DepartmentResponseDto;
import com.moon.project_two.Entity.Department;
import com.moon.project_two.Entity.Doctor;
import com.moon.project_two.Repository.DepartmentRepository;
import com.moon.project_two.Repository.DoctorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository; 
    private final ModelMapper modelMapper;
    private final DoctorRepository doctorRepository;

    @Transactional
    public DepartmentResponseDto createDepartment(DepartmentDto addDepartmentDto){

        Department department = new Department();
        department.setDepartmentName(addDepartmentDto.getDepartmentName());

        Department savedDepartment = departmentRepository.save(department);

        return modelMapper.map(savedDepartment, DepartmentResponseDto.class);

    }

    @Transactional
    public DepartmentResponseDto assignHeadDoctor(Long doctor_id, Long dept_id) {
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow(); 
        Department department = departmentRepository.findById(dept_id).orElseThrow();

        boolean flag = department.getDoctors().stream().anyMatch(x -> x.getId().equals(doctor.getId()));

        if(!flag){
            department.getDoctors().add(doctor);
            doctor.getDepartments().add(department);
        }

        department.setHeadDoctor(doctor);
        
        DepartmentResponseDto departmentResponseDto =  modelMapper.map(department, DepartmentResponseDto.class);
        
        departmentResponseDto.setHeadDoctor_id(doctor_id);

        return departmentResponseDto;
    }

    @Transactional
    public DepartmentResponseDto addDoctorToDepartment(Long doctor_id, Long dept_id) {
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow(); 
        Department department = departmentRepository.findById(dept_id).orElseThrow();

        boolean flag = department.getDoctors().stream().anyMatch(x -> x.getId().equals(doctor.getId()));

        if(!flag){
            department.getDoctors().add(doctor);
            doctor.getDepartments().add(department);
        }
        
        DepartmentResponseDto departmentResponseDto =  modelMapper.map(department, DepartmentResponseDto.class);
        
        departmentResponseDto.setHeadDoctor_id(department.getHeadDoctor().getId());

        return departmentResponseDto;

    }
}
