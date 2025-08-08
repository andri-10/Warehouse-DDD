package com.andri.warehouse.infrastructure.security.config;

import com.andri.warehouse.infrastructure.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        // Public endpoints (no authentication required)
                        .requestMatchers(HttpMethod.POST, "/api/users/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users/request-password-reset").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users/reset-password").permitAll()

                        // CLIENT endpoints
                        .requestMatchers(HttpMethod.POST, "/api/orders").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.POST, "/api/orders/*/submit").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.POST, "/api/orders/*/items").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/orders/*/items/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/orders/*/items/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.POST, "/api/orders/*/cancel").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/orders").hasAnyRole("CLIENT", "WAREHOUSE_MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/orders/*").hasAnyRole("CLIENT", "WAREHOUSE_MANAGER")

                        // WAREHOUSE_MANAGER endpoints
                        .requestMatchers(HttpMethod.POST, "/api/orders/*/approve").hasRole("WAREHOUSE_MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/orders/*/decline").hasRole("WAREHOUSE_MANAGER")
                        .requestMatchers("/api/items/**").hasRole("WAREHOUSE_MANAGER")
                        .requestMatchers("/api/trucks/**").hasRole("WAREHOUSE_MANAGER")
                        .requestMatchers("/api/deliveries/**").hasRole("WAREHOUSE_MANAGER")

                        // SYSTEM_ADMIN endpoints
                        .requestMatchers("/api/users").hasRole("SYSTEM_ADMIN")
                        .requestMatchers("/api/users/*").hasRole("SYSTEM_ADMIN")
                        .requestMatchers("/api/users/*/role").hasRole("SYSTEM_ADMIN")
                        .requestMatchers("/api/users/*/activate").hasRole("SYSTEM_ADMIN")
                        .requestMatchers("/api/users/*/deactivate").hasRole("SYSTEM_ADMIN")
                        .requestMatchers("/api/users/clients").hasRole("SYSTEM_ADMIN")
                        .requestMatchers("/api/users/warehouse-managers").hasRole("SYSTEM_ADMIN")
                        .requestMatchers("/api/users/role/*").hasRole("SYSTEM_ADMIN")
                        .requestMatchers("/api/users/username/*/exists").hasRole("SYSTEM_ADMIN")
                        .requestMatchers("/api/users/email/*/exists").hasRole("SYSTEM_ADMIN")
                        .requestMatchers("/api/users/search").hasRole("SYSTEM_ADMIN")
                        .requestMatchers("/api/users/bulk").hasRole("SYSTEM_ADMIN")

                        // Profile endpoints (all authenticated users)
                        .requestMatchers("/api/users/profile").hasAnyRole("CLIENT", "WAREHOUSE_MANAGER", "SYSTEM_ADMIN")
                        .requestMatchers("/api/users/change-password").hasAnyRole("CLIENT", "WAREHOUSE_MANAGER", "SYSTEM_ADMIN")
                        .requestMatchers("/api/users/logout").hasAnyRole("CLIENT", "WAREHOUSE_MANAGER", "SYSTEM_ADMIN")

                        // All other requests need authentication
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
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
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}