package com.moon.project_two.Service;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moon.project_two.DTO.DoctorResponseDto;
import com.moon.project_two.DTO.DepartmentDto;
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
        //puts the doctor in persistant context, insert query is executed
        Doctor savedDoctor = doctorRepository.save(doctor); 
        return modelMapper.map(savedDoctor, DoctorResponseDto.class);
    }

    @Transactional
    public void deleteDoctor(Long id) {
        //runs immediately
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
/* 
        beginTransaction();
        try {
            result = yourMethod();   // your code executes
            flush();                 // Hibernate executes pending SQL
            commitTransaction();     // database commit
        }
        catch(Exception e) {
            rollbackTransaction();
        }
        return result;  
*/
          

    @Transactional
    public DoctorResponseDto addNewDoctorandDept(DoctorDto DoctorDto, DepartmentDto departmentDto) {

        // TRANSIENT state (not managed by Hibernate yet)
        Doctor doctor = modelMapper.map(DoctorDto, Doctor.class);

        // TRANSIENT state
        Department department = modelMapper.map(departmentDto, Department.class);

        // Still TRANSIENT.
        // Establishing an in-memory relationship between doctor and department.
        doctor.getDepartments().add(department);

        // Still TRANSIENT.
        // Keeping the bidirectional relationship consistent in memory.
        department.getDoctors().add(doctor);

        // save() internally calls EntityManager.persist(doctor)
        //
        // doctor enters the MANAGED state (added to the persistence context).
        //
        // Because Doctor.id uses GenerationType.IDENTITY,
        // Hibernate must execute the INSERT immediately to obtain the generated ID.
        //
        // SQL executed here:
        // INSERT INTO doctor (...)
        //
        // The database generates the ID, and JDBC returns it using getGeneratedKeys().
        // Hibernate then sets doctor.id on the managed entity.
        //
        // Because cascade = PERSIST is configured on doctor.departments,
        // Hibernate detects the referenced department and automatically
        // calls persist(department).
        //
        // department now also becomes MANAGED (but its INSERT may still wait until flush).
        Doctor doctor2 = doctorRepository.save(doctor);

        // Hibernate now knows doctor.id.
        //
        // The INSERT for department and the join table row are usually deferred
        // until the flush phase (which happens before transaction commit).
        //
        // During flush Hibernate executes SQL in dependency order:
        //
        // 1. INSERT department
        // 2. INSERT row into join table (doctor_department)
        //
        // The join table row uses the generated doctor.id and department.id.

        // After flush completes, Spring commits the database transaction.

        // Returns the managed entity mapped to DTO (doctor2 is the same managed object).
        return modelMapper.map(doctor2, DoctorResponseDto.class);
    }

}
