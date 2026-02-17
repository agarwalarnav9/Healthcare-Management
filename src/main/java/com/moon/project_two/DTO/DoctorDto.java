package com.moon.project_two.DTO;

import com.moon.project_two.DTO.ValidationGroups.OnCreate;
import com.moon.project_two.DTO.ValidationGroups.OnUpdate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    
    @NotNull(groups = OnUpdate.class)
    private Long id;
    
    @NotBlank(groups = OnCreate.class)
    @Size(max = 50, groups = {OnCreate.class, OnUpdate.class})
    private String name; 

    @NotBlank(groups = OnCreate.class)
    @Email(groups = {OnCreate.class, OnUpdate.class})
    private String email; 

    @NotBlank(groups = OnCreate.class)
    @Size(max = 50, groups = {OnCreate.class, OnUpdate.class})
    private String specialization; 

}
