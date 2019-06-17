package com.techprimers.security.springsecurityauthserver.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails extends Users implements UserDetails {

    public CustomUserDetails(final Users userList){
        super(userList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        System.out.println("C7");
        System.out.println("ROLES"+getRoles().stream().findFirst().get().getRole());

        List at =  getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole()))
                .collect(Collectors.toList());
        System.out.println(at.toString());
        return at;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getName();
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
