package com.moon.project_two.DTO.ExtrnalApiResponse;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
public class LabReportResponse {
    
    private String labLocation; 
    private String status; 
    private String testReport; 
    private LocalDateTime createdAt; 
}
