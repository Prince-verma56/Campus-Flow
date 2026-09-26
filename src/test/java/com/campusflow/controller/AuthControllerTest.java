package com.campusflow.controller;

import com.campusflow.dto.auth.AuthLoginRequest;
import com.campusflow.dto.auth.AuthRegisterRequest;
import com.campusflow.dto.auth.AuthResponse;
import com.campusflow.dto.auth.CurrentUserResponse;
import com.campusflow.entity.Role;
import com.campusflow.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void register_ShouldReturn201() throws Exception {
        AuthRegisterRequest request = new AuthRegisterRequest();
        request.setEmail("student@example.com");
        request.setPassword("password");
        request.setRole(Role.STUDENT);
        AuthResponse response = new AuthResponse("token", 3600, new CurrentUserResponse(1L, "student@example.com", Role.STUDENT));

        when(authService.register(any(AuthRegisterRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").value("token"));
    }

    @Test
    void login_ShouldReturn200() throws Exception {
        AuthLoginRequest request = new AuthLoginRequest();
        request.setEmail("student@example.com");
        request.setPassword("password");
        AuthResponse response = new AuthResponse("token", 3600, new CurrentUserResponse(1L, "student@example.com", Role.STUDENT));

        when(authService.login(any(AuthLoginRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("token"));
    }
}
