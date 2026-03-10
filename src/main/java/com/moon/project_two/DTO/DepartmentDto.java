package com.moon.project_two.DTO;

import com.moon.project_two.DTO.ValidationGroups.OnCreate;
import com.moon.project_two.DTO.ValidationGroups.OnUpdate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    
    @NotNull(groups = OnUpdate.class)
    private Long id; 

    @NotNull(groups = OnCreate.class)
    private String departmentName;

    private Long headDoctorId;


}
