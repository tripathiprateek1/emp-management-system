package com.prateek.emp_management_system.service;

public interface AuthService {
    JwtResponseDTO login(LoginRequestDTO loginRequest);
}


