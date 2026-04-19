package com.prateek.emp_management_system.controller;


import com.prateek.emp_management_system.dto.ProjectAssignmentRequestDTO;
import com.prateek.emp_management_system.dto.ProjectAssignmentResponseDTO;
import com.prateek.emp_management_system.service.ProjectAssignmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class ProjectAssignmentController {

    @Autowired
    private ProjectAssignmentService projectAssignmentService;

    @PostMapping
    public ResponseEntity<ProjectAssignmentResponseDTO> assignEmployeeToProject(
            @RequestBody  @Valid ProjectAssignmentRequestDTO dto) {

        return ResponseEntity.ok(
                projectAssignmentService.assignEmployeeToProject(dto));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ProjectAssignmentResponseDTO>> getProjectsByEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                projectAssignmentService.getProjectsByEmployee(employeeId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectAssignmentResponseDTO>> getEmployeesByProject(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                projectAssignmentService.getEmployeesByProject(projectId));
    }

}
