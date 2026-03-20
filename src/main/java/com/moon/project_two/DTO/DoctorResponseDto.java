package com.moon.project_two.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorResponseDto {

    private Long id;
    private String name; 
    private String email; 
    private String specialization; 
}
