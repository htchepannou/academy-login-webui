package io.tchepannou.www.academy.login.backend.user;

import javax.validation.constraints.NotNull;

public class AuthRequest {
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }
}
