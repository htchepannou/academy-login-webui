package io.tchepannou.www.academy.login.controller;

import io.tchepannou.www.academy.login.support.HandlerStub;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = {"stub"})
public class LoginControllerIT {
    @Value("${application.backend.UserBackend.port}")
    private int port;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private LoginController controller;

    private Server server;

    private HandlerStub handler;
    private MockMvc mockMvc;

    private ExtendedModelMap model;

    @Before
    public void setUp () throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.model = new ExtendedModelMap();

        this.handler = new HandlerStub();

        this.server = new Server(this.port);
        this.server.setHandler(this.handler);
        this.server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void index() throws Exception {
        // WHEN
        final String result = controller.index("http://www.test-index.com", model);

        // THEN
        assertThat(result).isEqualTo("login");
        assertThat(model).hasSize(1);
        assertThat(model.get("done")).isEqualTo("http://www.test-index.com");
    }

    @Test
    public void signin() throws Exception {
        // GIVEN
        final LoginForm form = new LoginForm();
        form.setEmail("ray.sponsible@gmail.com");
        form.setPassword("ray123");
        form.setDone("http://www.test-signin.com");

        // WHEN
        final String result = controller.signin(form, model);

        // THEN
        assertThat(result).isEqualTo("redirect:http://www.test-signin.com");
        assertThat(model).isEmpty();
    }

    @Test
    public void signinAuthFailed() throws Exception {
        // GIVEN
        handler.setStatus(409);

        final LoginForm form = new LoginForm();
        form.setEmail("ray.sponsible@gmail.com");
        form.setPassword("invalid-password");
        form.setDone("http://www.test-signin.com");

        // WHEN
        final String result = controller.signin(form, model);

        // THEN
        assertThat(result).isEqualTo("login");

        assertThat(model).hasSize(3);
        assertThat(model.get("done")).isEqualTo("http://www.test-signin.com");
        assertThat(model.get("email")).isEqualTo("ray.sponsible@gmail.com");
        assertThat((boolean)model.get("authError")).isTrue();
    }
}
