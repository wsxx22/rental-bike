package com.example.rentalbike.app.security;

import com.example.rentalbike.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.getIsAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getIsAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.getIsCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getIsEnabled();
    }
}
