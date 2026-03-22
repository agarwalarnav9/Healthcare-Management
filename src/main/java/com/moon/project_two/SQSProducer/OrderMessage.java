package com.moon.project_two.SQSProducer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage {
    private String orderId;
    private String customerId;
    private String product;
    private Double amount;
}