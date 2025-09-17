package com.evcharger.architecture.config;

import com.evcharger.architecture.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {

        HttpSecurity authorization = http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("*"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }).and().csrf().disable()
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                 .addFilterBefore((Filter) new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                 .addFilterAfter((Filter) new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                 .addFilterAt((Filter) new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                 .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                 .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                
                .authorizeRequests(authz -> authz
                        .antMatchers("/**").permitAll());
        authorization.formLogin(withDefaults());
        // http.httpBasic(hbc -> hbc.authenticationEntryPoint(new
        // CustomBasicAuthenticationEntryPoint()));
        // http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new
        // CustomAccessDeniedHandler()));
        return authorization.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
