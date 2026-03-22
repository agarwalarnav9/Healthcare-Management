package com.moon.project_two.SQSProducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.sqs.model.SqsException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SQSProducerService {
    
    //Spring's helper to send messages to SQS — hides all the low level SDK complexity
    private final SqsTemplate sqsTemplate;
    private final ObjectMapper objectMapper;

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    @Retry(name = "sqsProducer", fallbackMethod = "sendMessageFallback")
    public void sendMessage(OrderMessage order) {
        
        // Convert object to JSON string
        String messageBody = convertObjectToJson(order);
        // Send to SQS
        try {
            sqsTemplate.send(queueUrl, messageBody);
            log.info("Message sent successfully to SQS. OrderId: {}", order.getOrderId());
        } catch (SqsException ex){
            int statusCode = ex.statusCode();
            if (statusCode >= 500) {
                // AWS server error → retry
                log.warn("SQS server error. Retrying. orderId={}", order.getOrderId(), ex);
                throw new RetryableException("SQS server error", ex);
            } else {
                // 4xx → bad request/config → don't retry
                log.error("SQS client error. Not retrying. orderId={}", order.getOrderId(), ex);
                throw new NonRetryableException("SQS client error", ex);
            }
        } catch (SdkClientException ex){
            throw new RetryableException("Network error", ex);
        }
       
    }

    private String convertObjectToJson(OrderMessage order){
        try {
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException ex){
            throw new NonRetryableException("Order was not serialized", ex);
        }
    }

    // Same class as sendMessage()
    private void sendMessageFallback(OrderMessage order, Exception ex) {
        log.error("All SQS retry attempts exhausted. OrderId: {}. Consider saving to DB or alerting.",
            order.getOrderId(), ex);

        // Your choices here:
        // 1. Save the failed message to a DB table for manual reprocessing
        // 2. Throw a final exception to let the caller know it failed
        // 3. Push to a dead letter queue
        throw new RuntimeException(
        "Failed to send order " + order.getOrderId() + " to SQS after all retries.", ex);
    }
    


}
