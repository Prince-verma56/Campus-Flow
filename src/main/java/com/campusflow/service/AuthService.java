package com.campusflow.service;

import com.campusflow.dto.auth.AuthLoginRequest;
import com.campusflow.dto.auth.AuthRegisterRequest;
import com.campusflow.dto.auth.AuthResponse;
import com.campusflow.dto.auth.CurrentUserResponse;
import com.campusflow.entity.User;
import com.campusflow.exception.DuplicateResourceException;
import com.campusflow.repository.UserRepository;
import com.campusflow.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthResponse register(AuthRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("User already exists with email: " + request.getEmail());
        }

        User user = new User(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );

        User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(savedUser);

        return new AuthResponse(
                jwtToken,
                jwtService.getExpirationTime() / 1000,
                new CurrentUserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getRole())
        );
    }

    public AuthResponse login(AuthLoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);

        return new AuthResponse(
                jwtToken,
                jwtService.getExpirationTime() / 1000,
                new CurrentUserResponse(user.getId(), user.getEmail(), user.getRole())
        );
    }
}
