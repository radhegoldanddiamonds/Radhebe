package com.example.Radhebe.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;

// ApiResponse.java
@Data
@AllArgsConstructor
public class ApiResponse {
    private Boolean success;
    private String message;
}