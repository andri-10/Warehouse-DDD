package com.andri.warehouse.infrastructure.security.service;

import com.andri.warehouse.domain.user.User;
import com.andri.warehouse.domain.user.UserRepository;
import com.andri.warehouse.domain.user.Username;
import com.andri.warehouse.infrastructure.security.jwt.JwtTokenProvider;
import com.andri.warehouse.interfaces.rest.dto.user.request.LoginRequest;
import com.andri.warehouse.interfaces.rest.dto.user.response.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 JwtTokenProvider jwtTokenProvider,
                                 UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            String token = jwtTokenProvider.generateToken(authentication);

            User user = userRepository.findByUsername(Username.of(loginRequest.getUsername()))
                    .orElseThrow(() -> new BadCredentialsException("User not found"));

            return LoginResponse.from(user, token, jwtTokenProvider.getExpirationTime());

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    public void logout(String token) {

    }
}