package com.prateek.emp_management_system.dto;

import com.prateek.emp_management_system.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeRequestDTO {

    @NotBlank(message = "Name is mandatory")
    private String name ;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email ;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password ;

    @NotBlank(message = "Designation is manadatory")
    private String designation ;

    private Role role;
}
