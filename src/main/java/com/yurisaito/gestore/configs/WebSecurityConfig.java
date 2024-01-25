package com.yurisaito.gestore.configs;

import static com.yurisaito.gestore.entity.Permission.ADMIN_CREATE;
import static com.yurisaito.gestore.entity.Permission.ADMIN_DELETE;
import static com.yurisaito.gestore.entity.Permission.ADMIN_READ;
import static com.yurisaito.gestore.entity.Permission.ADMIN_UPDATE;
import static com.yurisaito.gestore.entity.Permission.MANAGER_READ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private static final String[] WHITE_LIST_URL = { "/auth/**" };

    @Autowired
    WebSecurityFilter webSecurityFilter;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    LogoutHandler logoutHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorize -> authorize.requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/product/getAll")
                        .hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                        .requestMatchers(HttpMethod.GET, "/api/v1/product/getOne")
                        .hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/product/create").hasAnyAuthority(ADMIN_CREATE.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/product/update").hasAnyAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/product/delete")
                        .hasAnyAuthority(ADMIN_DELETE.name())
                        .anyRequest().authenticated())

                .addFilterBefore(webSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(Customizer.withDefaults())
                .logout(logout -> logout.logoutUrl("/user/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(
                                (request, response, authentication) -> SecurityContextHolder.clearContext()))
                .cors(Customizer.withDefaults())
                .build();
    }
}
