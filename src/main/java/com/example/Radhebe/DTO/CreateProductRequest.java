package com.example.Radhebe.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

// CreateProductRequest.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Model is required")
    private String model;

    @DecimalMin(value = "0.0", message = "Gross weight must be positive")
    private BigDecimal grossWeight;

    @DecimalMin(value = "0.0", message = "Stone weight must be positive")
    private BigDecimal stoneWeight;

    @DecimalMin(value = "0.0", message = "S1 weight must be positive")
    private BigDecimal s1Weight;

    @DecimalMin(value = "0.0", message = "S2 weight must be positive")
    private BigDecimal s2Weight;

    @Min(value = 0, message = "Carats must be positive")
    private Integer carats;

    private String description;
    private String imageUrl;
    private List<String> images;
    private String barcodeNumber;
    private Boolean inStock = true;
}


