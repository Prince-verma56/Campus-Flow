package com.campusflow.controller;

import com.campusflow.dto.auth.AuthLoginRequest;
import com.campusflow.dto.auth.AuthRegisterRequest;
import com.campusflow.dto.auth.AuthResponse;
import com.campusflow.dto.auth.CurrentUserResponse;
import com.campusflow.entity.User;
import com.campusflow.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthLoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<CurrentUserResponse> getCurrentUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(new CurrentUserResponse(user.getId(), user.getEmail(), user.getRole()));
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // Since we are using stateless JWT tokens, actual token invalidation requires a blacklist
        // or client-side removal. We return 200 OK to indicate the client should discard the token.
        return ResponseEntity.ok().build();
    }
}
