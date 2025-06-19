package com.example.Radhebe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

// UpdateProductRequest.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    private String name;
    private String model;
    private BigDecimal grossWeight;
    private BigDecimal stoneWeight;
    private BigDecimal s1Weight;
    private BigDecimal s2Weight;
    private Integer carats;
    private String description;
    private String imageUrl;
    private List<String> images;
    private String barcodeNumber;
    private Boolean inStock;
}