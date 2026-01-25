package com.prateek.emp_management_system.service;

import com.prateek.emp_management_system.dto.CreateProjectRequestDTO;
import com.prateek.emp_management_system.dto.ProjectResponseDTO;

public class ProjectAssignmentServiceImp implements ProjectService{
    @Override
    public ProjectResponseDTO createProject(CreateProjectRequestDTO dto) {
        return null;
    }

    @Override
    public ProjectResponseDTO getProjectById(Long projectId) {
        return null;
    }

    @Override
    public Page<ProjectResponseDTO> getAllProjects(int page, int size, String sortBy) {
        return null;
    }


    public ProjectResponseDTO updateProject(Long projectId, CreateProjectRequestDTO dto) {
        return null;
    }

    @Override
    public void deleteProject(Long projectId) {

    }
}
