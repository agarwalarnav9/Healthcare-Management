package com.moon.project_two.SQSProducer;

public class NonRetryableException extends RuntimeException{
    NonRetryableException(String message, Throwable cause){
        super(message, cause);
    }
}
