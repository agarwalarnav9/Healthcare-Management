package com.moon.project_two.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moon.project_two.DTO.DoctorResponseDto;
import com.moon.project_two.DTO.ExtrnalApiResponse.LabReportResponse;
import com.moon.project_two.DTO.AssignHeadDoctorToDepartmentDto;

import com.moon.project_two.DTO.DepartmentDto;
import com.moon.project_two.DTO.DepartmentResponseDto;
import com.moon.project_two.DTO.DoctorDto;

import com.moon.project_two.DTO.ValidationGroups.OnCreate;
import com.moon.project_two.DTO.ValidationGroups.OnUpdate;
import com.moon.project_two.Service.DepartmentService;
import com.moon.project_two.Service.DoctorService;
import com.moon.project_two.Service.ExternalService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequestMapping ("/api/v1/admins")
@RestController
@RequiredArgsConstructor
@Validated
public class AdminController {
    
    private final DoctorService doctorService;  
    private final DepartmentService departmentService;
    private final ExternalService externalService;
  
    //create a doctor 
    @PostMapping("/doctors")
    public ResponseEntity<DoctorResponseDto> createDoctor(
                                                @Validated(OnCreate.class) @RequestBody DoctorDto addDoctorDto){
        
        DoctorResponseDto addDoctorResponseDto = doctorService.addDoctor(addDoctorDto); 
        
        return ResponseEntity.status(HttpStatus.CREATED).body(addDoctorResponseDto); 
    }    

    //Patch a doctors 
    @PatchMapping("/doctors")
    public ResponseEntity<DoctorResponseDto> patchDoctor(@Validated(OnUpdate.class) @RequestBody DoctorDto patchDoctorDto){

        DoctorResponseDto addDoctorResponseDto = doctorService.patchDoctor(patchDoctorDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(addDoctorResponseDto); 
    } 

    //create a department
    @PostMapping("/departments")
    public ResponseEntity<DepartmentResponseDto> createDepartment(@Validated(OnCreate.class) @RequestBody DepartmentDto addDepartmentDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.createDepartment(addDepartmentDto));

    }

    //make a head doctor
    @PostMapping("/departments/assign-head-doctor")
    public ResponseEntity<DepartmentResponseDto> AssignHeadDoctor(@Valid @RequestBody AssignHeadDoctorToDepartmentDto assignHeadDoctorToDepartmentDto){
        DepartmentResponseDto departmentResponseDto = departmentService.assignHeadDoctor(assignHeadDoctorToDepartmentDto.getDoctor_id(), assignHeadDoctorToDepartmentDto.getDept_id());
        
        return ResponseEntity.status(HttpStatus.OK).body(departmentResponseDto);
    }

    //add a doctor to department 
    @PostMapping("/departments/add-doctors")
    public ResponseEntity<DepartmentResponseDto> addDoctorToDepartment(@Valid @RequestBody AssignHeadDoctorToDepartmentDto assignHeadDoctorToDepartmentDto){
        
        DepartmentResponseDto departmentResponseDto = departmentService.addDoctorToDepartment(assignHeadDoctorToDepartmentDto.getDoctor_id(), assignHeadDoctorToDepartmentDto.getDept_id());
        
        return ResponseEntity.status(HttpStatus.OK).body(departmentResponseDto);
    }

    //delete a doctor
    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<Void> deleteDoctor(@NotNull @PathVariable Long id){
        
        doctorService.deleteDoctor(id);       
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    //Calling External API - to get lab report
    @GetMapping("/reports/{name}/{email}")
    public ResponseEntity<LabReportResponse> getReportforPatientByNameAndEmail(@PathVariable String name, @PathVariable @NotBlank String email){
            
        return ResponseEntity.status(HttpStatus.OK).body(externalService.getReportforPatientByNameAndEmail(name, email));
        
    }


}
