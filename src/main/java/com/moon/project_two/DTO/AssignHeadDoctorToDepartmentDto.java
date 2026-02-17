package com.moon.project_two.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignHeadDoctorToDepartmentDto {

    @NotNull
    private Long doctor_id; 
    @NotNull
    private Long dept_id; 
}
