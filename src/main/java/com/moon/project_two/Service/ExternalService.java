package com.moon.project_two.Service;

import org.springframework.stereotype.Service;


import com.moon.project_two.DTO.ExtrnalApiResponse.LabReportResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExternalService {
    
  
    private final RestTemplateClient restTemplateClient;

    public LabReportResponse getReportforPatientByNameAndEmail(String name, String email) {
        return restTemplateClient.getReportforPatientByNameAndEmail(name, email);
    }

    
}
