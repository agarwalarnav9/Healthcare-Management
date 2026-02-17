package com.moon.project_two.Exception.Response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    
    private final String message;
    private final HttpStatus httpStatus;


}
