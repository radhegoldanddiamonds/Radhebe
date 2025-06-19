package com.example.Radhebe.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// CreateRoomRequest.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomRequest {
    @NotNull(message = "User ID is required")
    private UUID userId;
}

