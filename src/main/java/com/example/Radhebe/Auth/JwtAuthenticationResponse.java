package com.example.Radhebe.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;

// JwtAuthenticationResponse.java
@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private UserPrincipal user;

    public JwtAuthenticationResponse(String accessToken, UserPrincipal user) {
        this.accessToken = accessToken;
        this.user = user;
    }
}