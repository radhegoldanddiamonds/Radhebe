package com.example.Radhebe.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// SignUpRequest.java
@Data
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String shopName;

    @NotBlank
    private String village;

    @NotBlank
    private String userType;
}
