package com.prateek.emp_management_system.controller;


import com.prateek.emp_management_system.dto.CreateProjectRequestDTO;
import com.prateek.emp_management_system.dto.ProjectResponseDTO;
import com.prateek.emp_management_system.dto.UpdateProjectRequestDTO;
import com.prateek.emp_management_system.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(
            @RequestBody CreateProjectRequestDTO dto) {

        ProjectResponseDTO response = projectService.createProject(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponseDTO>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        return ResponseEntity.ok(
                projectService.getAllProjects(page, size, sortBy));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> updateProject(
            @PathVariable Long projectId,
            @RequestBody UpdateProjectRequestDTO dto) {

        return ResponseEntity.ok(
                projectService.updateProject(projectId, dto));

    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(
            @PathVariable Long projectId) {

        projectService.deleteProject(projectId);
        return ResponseEntity.ok("Project deleted successfully");
    }

}