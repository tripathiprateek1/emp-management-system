package com.prateek.emp_management_system.service;

import com.prateek.emp_management_system.dto.EmployeeRequestDTO;
import com.prateek.emp_management_system.dto.EmployeeResponseDTO;

public class EmployeeServiceImp implements EmployeeService {
    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        return null;
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        return null;
    }

    @Override
    public Page<EmployeeResponseDTO> getAllEmployees(int page, int size, String sortBy) {
        return null;
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto) {
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {

    }
}
