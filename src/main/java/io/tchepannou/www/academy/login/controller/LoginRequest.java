package io.tchepannou.www.academy.login.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull
    @Min(1)
    private String email;

    @NotNull
    @Min(1)
    private String password;

    private String done;

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

    public String getDone() {
        return done;
    }

    public void setDone(final String done) {
        this.done = done;
    }
}
