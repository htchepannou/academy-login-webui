package io.tchepannou.www.academy.login.backend.user;

import io.tchepannou.www.academy.login.backend.BaseResponse;

public class AuthResponse extends BaseResponse {
    private SessionDto session;

    public SessionDto getSession() {
        return session;
    }

    public void setSession(final SessionDto session) {
        this.session = session;
    }
}
