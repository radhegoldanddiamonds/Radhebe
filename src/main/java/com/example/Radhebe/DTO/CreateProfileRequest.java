package com.example.Radhebe.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// CreateProfileRequest.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProfileRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotBlank(message = "Shop name is required")
    private String shopName;

    @NotBlank(message = "Village is required")
    private String village;

    @NotBlank(message = "User type is required")
    @Pattern(regexp = "^(Admin|Retailer|Wholesaler)$", message = "User type must be Admin, Retailer, or Wholesaler")
    private String userType;

    private UUID createdBy;
}

