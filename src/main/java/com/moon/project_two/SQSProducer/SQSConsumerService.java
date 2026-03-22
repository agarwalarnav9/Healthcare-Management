package com.moon.project_two.SQSProducer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SQSConsumerService {
    
    private final ObjectMapper objectMapper;

    @SqsListener("${aws.sqs.queue-url}")
    public void receiveMessage(String messageBody) {
        try {
            // Deserialize JSON string to object
            OrderMessage order = objectMapper.readValue(messageBody, OrderMessage.class);

            log.info("Order received - OrderId: {}, Product: {}, Amount: {}",
                    order.getOrderId(),
                    order.getProduct(),
                    order.getAmount());

            // process your order here
            processOrder(order);

        } catch (Exception e) {
            log.error("Failed to process message: {}", messageBody, e);
            throw new RuntimeException("Message processing failed", e);  
            // throwing exception = message goes back to queue and retried
        }
    }

    private void processOrder(OrderMessage order) {
        // your business logic here
        log.info("Processing order: {}", order.getOrderId());
    }
}
