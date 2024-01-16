package com.findar.findar.security;

import com.findar.findar.config.JwtAuthenticationFilter;
import com.findar.findar.service.InvalidLoginAttemptHandler;
import com.findar.findar.service.UserAuthDetailsService;
import com.findar.findar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserAuthDetailsService userAuthDetailsService;

    @Autowired
    private InvalidLoginAttemptHandler invalidLoginAttemptHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        return http.csrf(csrf -> csrf.disable()) .exceptionHandling((exception)-> exception.authenticationEntryPoint(invalidLoginAttemptHandler))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/books/**").authenticated()
                        .requestMatchers("/authenticate", "/register", "/css/**", "/js/**", "/images/**","/swagger-ui.html").permitAll()
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults()).addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }
}