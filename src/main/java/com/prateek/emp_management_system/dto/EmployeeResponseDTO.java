package com.prateek.emp_management_system.dto;

import com.prateek.emp_management_system.entity.Role;
import lombok.Data;

import java.time.LocalDate;


@Data
public class EmployeeResponseDTO {

    private Long id;
    private String name ;
    private String email ;
    private String designation;
    private Role role;
    private LocalDate dateOfJoining;
}
