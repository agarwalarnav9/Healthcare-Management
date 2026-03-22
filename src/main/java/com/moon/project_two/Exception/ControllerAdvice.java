package com.moon.project_two.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import software.amazon.awssdk.services.sqs.model.SqsException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moon.project_two.Exception.Response.ErrorResponse;
import com.moon.project_two.SQSProducer.NonRetryableException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DataIntegrityViolationException ex) {

        log.warn("Unique constraint violated", ex);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        "Resource already exists",
                        HttpStatus.CONFLICT
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        log.warn("Method argumnet not valid", ex); 

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
        .getFieldErrors()
        .forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors.toString(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NonRetryableException.class)
    public ResponseEntity<ErrorResponse> handleNonRetryableException(NonRetryableException ex){
        
        String message = ex.getMessage();         // your own message: "SQS client error"
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // default fallback
        
        // Dig into the cause — this is the original SqsException
        if (ex.getCause() instanceof SqsException sqsEx) {
                message = sqsEx.awsErrorDetails().errorMessage(); // actual AWS error message
                status = HttpStatus.valueOf(sqsEx.statusCode());  // actual AWS HTTP status code
        } else if (ex.getCause() instanceof JsonProcessingException sqsEx) {
                message = sqsEx.getMessage(); // actual AWS error message
                status = HttpStatus.BAD_REQUEST;  // actual AWS HTTP status code
        }

        return ResponseEntity
        .status(status)
        .body(new ErrorResponse(
                message, 
                status
        ));
    }
}
