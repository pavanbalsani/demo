package com.example.demo.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
        import java.io.PrintWriter;

@Component
public class AuthenticationService
        extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authEx) throws IOException {

        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("helloPavan");
        super.afterPropertiesSet();
    }
}