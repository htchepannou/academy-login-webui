package io.tchepannou.www.academy.login.controller;

import io.tchepannou.www.academy.login.backend.user.AuthException;
import io.tchepannou.www.academy.login.backend.user.AuthResponse;
import io.tchepannou.www.academy.login.backend.user.UserBackend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@ConfigurationProperties("application.controller.login")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserBackend userBackend;

    @RequestMapping(value="/login")
    public String index(
            @RequestParam(required = false) String done,
            final Model model
    ) {
        model.addAttribute("done", done);
        return "login";
    }

    @RequestMapping(value="/signin")
    public String signin(
            @ModelAttribute LoginForm form,
            final Model model
    ) throws IOException {
        try {

            final AuthResponse response = userBackend.login(form.getEmail(), form.getPassword(), "student");
            final String accessToken = response.getSession().getAccessToken();
            final String url = appendParam(form.getDone(), "guid", accessToken);

            return String.format("redirect:%s", url);

        } catch (AuthException e){
            LOGGER.error("Login failure", e);

            model.addAttribute("email", form.getEmail());
            model.addAttribute("done", form.getDone());
            model.addAttribute("authError", true);
            return "login";
        }
    }

    private String appendParam(final String url, final String name, final String value) {
        final StringBuilder sb = new StringBuilder(url);
        if (url.contains("?")){
            sb.append('&');
        } else {
            sb.append('?');
        }
        sb.append(name).append('=').append(value);
        return sb.toString();
    }
}
