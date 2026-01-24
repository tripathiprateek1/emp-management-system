package com.prateek.emp_management_system.service;


import com.prateek.emp_management_system.dto.EmployeeRequestDTO;
import com.prateek.emp_management_system.dto.EmployeeResponseDTO;
import com.prateek.emp_management_system.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {


    EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);
    EmployeeResponseDTO getEmployeeById(Long id);
    Page<EmployeeResponseDTO> getAllEmployees(int page, int size, String sortBy);
    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto);
    void deleteEmployee(Long id);
}
