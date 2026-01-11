package com.prateek.emp_management_system.service;

import com.prateek.emp_management_system.dto.ProjectRequestDTO;
import com.prateek.emp_management_system.dto.ProjectResponseDTO;

public interface ProjectService {

    ProjectResponseDTO createProject(ProjectRequestDTO dto);

    ProjectResponseDTO getProjectById(Long projectId);

    Page<ProjectResponseDTO> getAllProjects(int page, int size, String sortBy);

    ProjectResponseDTO updateProject(Long projectId, ProjectRequestDTO dto);

    void deleteProject(Long projectId);
}
