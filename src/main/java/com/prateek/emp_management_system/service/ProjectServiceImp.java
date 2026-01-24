package com.prateek.emp_management_system.service;

import com.prateek.emp_management_system.dto.ProjectRequestDTO;
import com.prateek.emp_management_system.dto.ProjectResponseDTO;
import com.prateek.emp_management_system.entity.Employee;
import com.prateek.emp_management_system.entity.Project;
import com.prateek.emp_management_system.entity.ProjectStatus;
import com.prateek.emp_management_system.entity.Role;
import com.prateek.emp_management_system.repository.EmployeeRepository;
import com.prateek.emp_management_system.repository.ProjectRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectServiceImp implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProjectResponseDTO createProject(ProjectRequestDTO dto) {

         if(projectRepository.existsByName(dto.getProjectName())) {
             throw new DuplicateRequestException("Project already exists with name: " + dto.getProjectName());
         }
         if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new ValidationException(
                    "End date cannot be before start date");
        }
        Employee manager = employeeRepository.findById(dto.getManagerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Manager not found with id: " + dto.getManagerId()));

        if (manager.getRole() != Role.MANAGER) {
            throw new ValidationException("User is not a Manager");
        }

        Project project = modelMapper.map(dto, Project.class);
        project.setManager(manager);
        project.setProjectStatus(ProjectStatus.PLANNED);
        Project savedProject = projectRepository.save(project);
        ProjectResponseDTO response =
                modelMapper.map(savedProject, ProjectResponseDTO.class);

        response.setManagerName(manager.getName());

        return response;
    }

    @Override
    public ProjectResponseDTO getProjectById(Long projectId) {
        return null;
    }

    @Override
    public Page<ProjectResponseDTO> getAllProjects(int page, int size, String sortBy) {
        return null;
    }

    @Override
    public ProjectResponseDTO updateProject(Long projectId, ProjectRequestDTO dto) {
        return null;
    }

    @Override
    public void deleteProject(Long projectId) {

    }
}
