package com.moon.project_two.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.moon.project_two.SQSProducer.OrderMessage;
import com.moon.project_two.SQSProducer.SQSProducerService;

@Slf4j
@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final SQSProducerService sqsProducerService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody OrderMessage order) {
        log.info("Received request to send message for OrderId: {}", order.getOrderId());
        sqsProducerService.sendMessage(order);
        return ResponseEntity.ok("Message sent successfully!");
    }
}
