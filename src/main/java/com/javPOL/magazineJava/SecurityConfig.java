package com.javPOL.magazineJava;

import com.javPOL.magazineJava.enums.Role;
import com.javPOL.magazineJava.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter)
            throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/categories/**").hasRole(Role.EMPLOYEE.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole(Role.EMPLOYEE.name())
                        .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasRole(Role.EMPLOYEE.name())
                        .requestMatchers(HttpMethod.PATCH, "/api/categories/**").hasRole(Role.EMPLOYEE.name())
                        .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()

                        .requestMatchers("/h2-console/**").permitAll()

                        .requestMatchers("/api/cart/**").authenticated()

                        .requestMatchers("/api/admin/**").hasRole(Role.ADMIN.name())
                        .requestMatchers("/api/users/**").hasRole(Role.ADMIN.name())
                        .requestMatchers("/api/suppliers/**").hasRole(Role.EMPLOYEE.name())
                        .requestMatchers("/api/warehouses/**").hasRole(Role.EMPLOYEE.name())
                        .requestMatchers("/api/invoices/**").hasRole(Role.EMPLOYEE.name())
                        .requestMatchers(HttpMethod.POST, "/api/orders/**").hasRole(Role.USER.name())
                        .requestMatchers("/api/orders/**").hasRole(Role.ADMIN.name())
                        .requestMatchers("/api/customers/**").hasRole(Role.ADMIN.name())
                        .requestMatchers("/api/product-suppliers/**").hasRole(Role.EMPLOYEE.name())
                        .requestMatchers("/api/product-orders/**").hasRole(Role.EMPLOYEE.name())

                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_EMPLOYEE > ROLE_USER");
        return roleHierarchy;
    }
}
