package com.example.springbootc51.dto;

import javax.validation.constraints.NotNull;

public class UserDTO {
    private static final String MSG_NAME_EMPTY = "name empty";
    private static final String MSG_PASSWORD_EMPTY = "password empty";

    private long id;

    @NotNull(message = MSG_NAME_EMPTY)
    private String name;

    @NotNull(message = MSG_PASSWORD_EMPTY)
    private String password;

    public UserDTO() {
    }

    public UserDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
