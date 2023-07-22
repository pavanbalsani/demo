package com.example.demo.configuration;

import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter {

    @Autowired private AuthenticationService authenticationEntryPoint;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests(auth -> {
            try {
                auth.requestMatchers(toH2Console()).permitAll()
                        .requestMatchers("/employeeservices/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/employeeservices/employees/addEmployeeDetails").permitAll()
                        .requestMatchers(antMatcher(HttpMethod.DELETE, "/employeeservices/**")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.PUT, "/employeeservices/**")).permitAll()
                        .anyRequest().authenticated()
                        .and().httpBasic(withDefaults())
                        .csrf().ignoringRequestMatchers(toH2Console())
                        .and().headers().frameOptions().sameOrigin();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        http.csrf().disable();
        return http.build();
    }
}