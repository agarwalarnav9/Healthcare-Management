package com.moon.project_two.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDto {
    
    private Long id; 
    private String departmentName;
    private Long headDoctor_id;

}
