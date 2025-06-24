package com.example.Radhebe.Auth;

import com.example.Radhebe.Entity.Profile;
import com.example.Radhebe.Entity.UserType;
import com.example.Radhebe.Repository.ProfileRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String jwt = jwtTokenUtil.generateToken(userPrincipal);

            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, userPrincipal));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Invalid email or password"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        // Create new profile
        Profile profile = new Profile();
        profile.setUsername(signUpRequest.getUsername());
        profile.setEmail(signUpRequest.getEmail());
        profile.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        profile.setPhoneNumber(signUpRequest.getPhoneNumber());
        profile.setShopName(signUpRequest.getShopName());
        profile.setVillage(signUpRequest.getVillage());
        profile.setUserType(UserType.valueOf(signUpRequest.getUserType()));

        Profile result = profileRepository.save(profile);

        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }
}
