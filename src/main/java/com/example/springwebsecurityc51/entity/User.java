package com.example.springwebsecurityc51.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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

    @NotNull(message = MSG_NAME_EMPTY)
//    @Size(min = 3, max = 50, message = MSG_NAME_3_TO_50_CHARACTERS)
    private String name;

    @NotNull(message = MSG_NAME_EMPTY)
//    @Size(min = 3, max = 50, message = MSG_NAME_3_TO_50_CHARACTERS)
    private String username;

    @NotNull(message = MSG_PASSWORD_EMPTY)
//    @Size(min = 3, max = 50, message = MSG_PASSWORD_3_TO_50_CHARACTERS)
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

      @NotNull(message = MSG_EMAIL_EMPTY)
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = MSG_EMAIL_NOT_VALID)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Operation> operationList;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public void setOperationList(List<Operation> operationList) {
        if (operationList != null) {
            operationList.forEach(o -> o.setUser(this));
        }
        this.operationList = operationList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

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
