package com.moon.project_two.Service;

import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpMethod;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.moon.project_two.DTO.ExtrnalApiResponse.LabReportResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExternalService {
    
    @Qualifier("labReportRestTemplate")
    private final RestTemplate restTemplate;

    public LabReportResponse getReportforPatientByNameAndEmail(String name, String email) {
        String url = UriComponentsBuilder
            .fromPath("/{name}/{email}")
            .build()
            .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
         
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        Map<String, Object> uriVariables = Map.of("name", name, "email", email);
        
        ResponseEntity<LabReportResponse> response = 
                        restTemplate.exchange(url, 
                        HttpMethod.GET, 
                        entity, 
                        LabReportResponse.class, 
                        uriVariables);

        return response.getBody();

    }

}
