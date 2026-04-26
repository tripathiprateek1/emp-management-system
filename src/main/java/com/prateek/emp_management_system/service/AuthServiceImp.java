package com.prateek.emp_management_system.service;

import com.prateek.emp_management_system.dto.JwtResponseDTO;
import com.prateek.emp_management_system.dto.LoginRequestDTO;
import com.prateek.emp_management_system.entity.Employee;
import com.prateek.emp_management_system.repository.EmployeeRepository;
import com.prateek.emp_management_system.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthServiceImp implements AuthService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public JwtResponseDTO login(LoginRequestDTO dto) {

        Employee employee = employeeRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(dto.getPassword(), employee.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(
                employee.getEmail(),
                employee.getRole().name()
        );

        return new JwtResponseDTO(token);
    }
}
