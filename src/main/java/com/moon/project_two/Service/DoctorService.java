package com.moon.project_two.Service;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moon.project_two.DTO.DoctorResponseDto;
import com.moon.project_two.DTO.DoctorDto;
import com.moon.project_two.Entity.Department;
import com.moon.project_two.Entity.Doctor;
import com.moon.project_two.Repository.DoctorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorService {
    
    private final DoctorRepository doctorRepository; 
    private final ModelMapper modelMapper; 

    @Transactional
    public DoctorResponseDto addDoctor(DoctorDto addDoctorDto){

        Doctor doctor = modelMapper.map(addDoctorDto, Doctor.class);
        Doctor savedDoctor = doctorRepository.save(doctor); 

        return modelMapper.map(savedDoctor, DoctorResponseDto.class);

    }

    @Transactional
    public void deleteDoctor(Long id) {
        
        Doctor doctor = doctorRepository.findById(id).orElseThrow(); 
        List<Department> departments = doctor.getDepartments();

        for(Department department : departments){
            department.getDoctors().remove(doctor);
            if (department.getHeadDoctor().getId().equals(doctor.getId())){
                department.setHeadDoctor(null);
            }
        }

        doctorRepository.delete(doctor);

    }

   @Transactional
    public DoctorResponseDto patchDoctor(DoctorDto patchDoctorDto) {
        
        Doctor doctor = doctorRepository.findById(patchDoctorDto.getId()).orElseThrow(); 

        if(patchDoctorDto.getName() != null){
            doctor.setName(patchDoctorDto.getName());
        }
        if(patchDoctorDto.getEmail() != null){
            doctor.setEmail(patchDoctorDto.getEmail());
        }
        if(patchDoctorDto.getSpecialization() != null){
            doctor.setSpecialization(patchDoctorDto.getSpecialization());
        }

        return modelMapper.map(doctor, DoctorResponseDto.class);

    }


}
