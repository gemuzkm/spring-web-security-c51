package com.example.springwebsecurityc51.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User implements UserDetails {
    private static final String MSG_NAME_EMPTY = "name empty";
    private static final String MSG_NAME_3_TO_50_CHARACTERS = "name should be between 3 and 50 characters";
    private static final String MSG_PASSWORD_EMPTY = "password empty";
    private static final String MSG_PASSWORD_3_TO_50_CHARACTERS = "password should be between 3 and 50 characters";
    private static final String MSG_EMAIL_EMPTY = "email empty";
    private static final String MSG_EMAIL_NOT_VALID = "not valid email";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
