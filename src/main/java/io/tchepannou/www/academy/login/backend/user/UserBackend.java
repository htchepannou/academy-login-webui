package io.tchepannou.www.academy.login.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
@ConfigurationProperties("application.backend.user")
public class UserBackend {
    private String url;

    @Autowired
    private RestTemplate rest;


    public AuthResponse login(
            final String email,
            final String password,
            final String role
    ) throws AuthException, IOException {
        final AuthRequest request = new AuthRequest();
        request.setEmail(email);
        request.setPassword(password);
        request.setRole(role);
        try{

            final String uri = String.format("%s/academy/v1/auth", this.url);
            return rest.postForEntity(uri, request, AuthResponse.class).getBody();

        } catch (RestClientResponseException e){
            throw new AuthException(e.getRawStatusCode(), e.getResponseBodyAsString(), e);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }
}
