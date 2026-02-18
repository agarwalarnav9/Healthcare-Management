package com.moon.project_two.DTO.ExtrnalApiResponse;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class LabReportResponse {
    
    private String labLocation; 
    private String status; 
    private String testReport; 
    private LocalDateTime createdAt; 
}
