package com.prateek.emp_management_system.service;

import com.prateek.emp_management_system.dto.ProjectAssignmentRequestDTO;
import com.prateek.emp_management_system.dto.ProjectAssignmentResponseDTO;

import java.util.List;

public interface ProjectAssignmentService {

    // Assign an employee to a project
    ProjectAssignmentResponseDTO assignEmployeeToProject(ProjectAssignmentRequestDTO dto);

    // Get all projects assigned to an employee
    List<ProjectAssignmentResponseDTO> getProjectsByEmployee(Long employeeId);

    // Get all employees assigned to a project
    List<ProjectAssignmentResponseDTO> getEmployeesByProject(Long projectId);
}
