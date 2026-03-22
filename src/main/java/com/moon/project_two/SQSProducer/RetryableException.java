package com.moon.project_two.SQSProducer;

public class RetryableException extends RuntimeException {
    RetryableException(String message, Throwable cause){
        super(message, cause);
    }
}
