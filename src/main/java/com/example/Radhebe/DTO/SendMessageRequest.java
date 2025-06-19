package com.example.Radhebe.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// SendMessageRequest.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageRequest {
    @NotNull(message = "Room ID is required")
    private UUID roomId;

    @NotNull(message = "Sender ID is required")
    private UUID senderId;

    @NotBlank(message = "Message content is required")
    private String message;
}
