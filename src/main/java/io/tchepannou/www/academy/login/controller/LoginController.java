package io.tchepannou.www.academy.login.controller;

import io.tchepannou.www.academy.login.backend.user.AuthException;
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

    private String defaultSigninRedirectUrl;

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
            @ModelAttribute LoginRequest request,
            final Model model
    ) throws IOException {
        try {

            userBackend.login(request.getEmail(), request.getPassword(), "student");

            return request.getDone() == null
                    ? "redirect:" + defaultSigninRedirectUrl
                    : "redirect:" + request.getDone();

        } catch (AuthException e){
            LOGGER.error("Login failure", e);

            model.addAttribute("email", request.getEmail());
            model.addAttribute("done", request.getDone());
            model.addAttribute("authError", true);
            return "login";
        }
    }

    public String getDefaultSigninRedirectUrl() {
        return defaultSigninRedirectUrl;
    }

    public void setDefaultSigninRedirectUrl(final String defaultSigninRedirectUrl) {
        this.defaultSigninRedirectUrl = defaultSigninRedirectUrl;
    }
}
