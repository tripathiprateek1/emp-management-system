package com.prateek.emp_management_system.controller;


import com.prateek.emp_management_system.dto.LoginRequestDTO;
import com.prateek.emp_management_system.entity.Employee;
import com.prateek.emp_management_system.repository.EmployeeRepository;
import com.prateek.emp_management_system.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO dto) {

        Employee employee = employeeRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(dto.getPassword(), employee.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(employee.getEmail(), employee.getRole().name());
    }
}
