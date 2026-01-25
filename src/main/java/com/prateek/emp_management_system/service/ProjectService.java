package com.prateek.emp_management_system.service;

import com.prateek.emp_management_system.dto.CreateProjectRequestDTO;
import com.prateek.emp_management_system.dto.ProjectResponseDTO;
import com.prateek.emp_management_system.dto.UpdateProjectRequestDTO;

public interface ProjectService {

    ProjectResponseDTO createProject(CreateProjectRequestDTO dto);

    ProjectResponseDTO getProjectById(Long projectId);

    Page<ProjectResponseDTO> getAllProjects(int page, int size, String sortBy);

    ProjectResponseDTO updateProject(Long projectId, UpdateProjectRequestDTO dto);

    void deleteProject(Long projectId);
}
