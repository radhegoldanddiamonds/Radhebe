package com.example.Radhebe.Auth;

import com.example.Radhebe.Entity.Profile;
import com.example.Radhebe.Entity.UserType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserPrincipal implements UserDetails {
    private UUID id;
    private String email;
    private String password;
    private String username;
    private UserType userType;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(UUID id, String email, String password, String username,
                         UserType userType, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.userType = userType;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Profile profile) {
        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_" + profile.getUserType().name())
        );

        return new UserPrincipal(
                profile.getId(),
                profile.getEmail(),
                profile.getPassword(),
                profile.getUsername(),
                profile.getUserType(),
                authorities
        );
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
