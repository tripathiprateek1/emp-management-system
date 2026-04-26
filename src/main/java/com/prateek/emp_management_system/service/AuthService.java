package com.prateek.emp_management_system.service;

import com.prateek.emp_management_system.dto.JwtResponseDTO;
import com.prateek.emp_management_system.dto.LoginRequestDTO;

public interface AuthService {
    JwtResponseDTO login(LoginRequestDTO loginRequest);
}


