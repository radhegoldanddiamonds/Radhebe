package com.example.Radhebe.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

// AddToCartRequest.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {
    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Product ID is required")
    private UUID productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private BigDecimal neededGrossWeight;
    private BigDecimal neededNetWeight;
}

