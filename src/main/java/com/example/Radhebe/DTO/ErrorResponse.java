package com.example.Radhebe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// ErrorResponse.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
