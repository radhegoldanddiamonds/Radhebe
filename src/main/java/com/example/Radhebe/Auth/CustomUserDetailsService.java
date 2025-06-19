package com.example.Radhebe.Auth;

import com.example.Radhebe.Entity.Profile;
import com.example.Radhebe.Repository.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Profile profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        return UserPrincipal.create(profile);
    }

    @Transactional
    public UserDetails loadUserById(UUID id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + id));

        return UserPrincipal.create(profile);
    }
}
