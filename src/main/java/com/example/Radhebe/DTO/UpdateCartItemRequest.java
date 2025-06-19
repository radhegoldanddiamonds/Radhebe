package com.example.Radhebe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// UpdateCartItemRequest.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartItemRequest {
    private Integer quantity;
    private BigDecimal neededGrossWeight;
    private BigDecimal neededNetWeight;
    private String status; // Pending, Ordered, Completed
}
