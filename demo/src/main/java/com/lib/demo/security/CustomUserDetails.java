package com.lib.demo.security;

import com.lib.demo.entities.Role;
import com.lib.demo.entities.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class CustomUserDetails implements UserDetails {
    private String cusUsername;
    private String cusPassword;
    private String cusEmail;
    private String cusStatus;
    private Collection<? extends GrantedAuthority> cusRoles;


    public static CustomUserDetails fromUser(User currentUser) {
        List<GrantedAuthority> listAuthorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(currentUser.getRole().getRoleName());
        listAuthorities.add(simpleGrantedAuthority);

        return new CustomUserDetails(
                currentUser.getUsername(),
                currentUser.getPassword(),
                currentUser.getEmail(),
                currentUser.getStatus(),
                listAuthorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.cusRoles;
    }

    @Override
    public String getPassword() {
        return this.cusPassword;
    }

    @Override
    public String getUsername() {
        return this.cusUsername;
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
