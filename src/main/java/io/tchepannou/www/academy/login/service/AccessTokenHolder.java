package io.tchepannou.www.academy.login.service;

import io.tchepannou.www.academy.login.backend.user.SessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Component
@ConfigurationProperties("application.service.AccessTokenHolder")
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccessTokenHolder {
    private static final String COOKIE_NAME = "guid";

    private String domain;

    @Autowired
    private HttpServletResponse response;

    public void set(final SessionDto session){
        final Cookie cookie = new Cookie(COOKIE_NAME, session.getAccessToken());
//        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(final String domain) {
        this.domain = domain;
    }
}
