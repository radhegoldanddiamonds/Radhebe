package com.example.Radhebe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// UpdateProfileRequest.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest {
    private String username;
    private String phoneNumber;
    private String shopName;
    private String village;
    private String userType;
}
