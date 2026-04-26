package com.prateek.emp_management_system.controller;


import com.prateek.emp_management_system.dto.ProjectAssignmentRequestDTO;
import com.prateek.emp_management_system.dto.ProjectAssignmentResponseDTO;
import com.prateek.emp_management_system.service.ProjectAssignmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class ProjectAssignmentController {

    @Autowired
    private ProjectAssignmentService projectAssignmentService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ProjectAssignmentResponseDTO> assignEmployeeToProject(
            @RequestBody  @Valid ProjectAssignmentRequestDTO dto) {

        return ResponseEntity.ok(
                projectAssignmentService.assignEmployeeToProject(dto));
    }

    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER','ADMIN')")
    public ResponseEntity<List<ProjectAssignmentResponseDTO>> getProjectsByEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                projectAssignmentService.getProjectsByEmployee(employeeId));
    }

    @GetMapping("/project/{projectId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<ProjectAssignmentResponseDTO>> getEmployeesByProject(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                projectAssignmentService.getEmployeesByProject(projectId));
    }

}
