package com.prateek.emp_management_system.service;

import com.prateek.emp_management_system.dto.CreateProjectRequestDTO;
import com.prateek.emp_management_system.dto.ProjectResponseDTO;
import com.prateek.emp_management_system.dto.UpdateProjectRequestDTO;
import com.prateek.emp_management_system.entity.Employee;
import com.prateek.emp_management_system.entity.Project;
import com.prateek.emp_management_system.entity.ProjectStatus;
import com.prateek.emp_management_system.entity.Role;
import com.prateek.emp_management_system.exception.EmployeeNotFoundException;
import com.prateek.emp_management_system.exception.ProjectNotFoundException;
import com.prateek.emp_management_system.exception.ValidationException;
import com.prateek.emp_management_system.repository.EmployeeRepository;
import com.prateek.emp_management_system.repository.ProjectAssignmentRepository;
import com.prateek.emp_management_system.repository.ProjectRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ProjectServiceImp implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectAssignmentRepository projectAssignmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProjectResponseDTO createProject(CreateProjectRequestDTO dto) {

         if(projectRepository.existsByName(dto.getProjectName())) {
             throw new DuplicateRequestException("Project already exists with name: " + dto.getProjectName());
         }
         if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new ValidationException(
                    "End date cannot be before start date");
        }
        Employee manager = employeeRepository.findById(dto.getManagerId())
                .orElseThrow(() -> new EmployeeNotFoundException(
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
        Project  project=projectRepository.findById(projectId).orElseThrow(() ->
                 new ProjectNotFoundException("Project not found with id: " + projectId));
        ProjectResponseDTO dto =modelMapper.map(project, ProjectResponseDTO.class);
        dto.setManagerId(project.getManager().getId());
        dto.setManagerName(project.getManager().getName());
        return dto;
    }

    @Override
    public Page<ProjectResponseDTO> getAllProjects(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Project> projectPage=projectRepository.findAll(pageable);


        return  projectPage.map(project  ->{
               ProjectResponseDTO dto = modelMapper.map(project,ProjectResponseDTO.class);
               dto.setManagerId(project.getManager().getId());
               dto.setManagerName(project.getManager().getName());
               return dto;
        });

    }

    @Override
    public ProjectResponseDTO updateProject(Long projectId, UpdateProjectRequestDTO dto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ProjectNotFoundException("Project not found with id: " + projectId));
        modelMapper.map(dto,Project.class);
        if(dto.getProjectStatus()!=null){
            project.setProjectStatus(dto.getProjectStatus());
        }
        if (dto.getManagerId() != null) {
            Employee manager = employeeRepository.findById(dto.getManagerId())
                    .orElseThrow(() -> new EmployeeNotFoundException(
                            "Manager not found with id: " + dto.getManagerId()));

            if (manager.getRole() != Role.MANAGER) {
                throw new ValidationException("User is not a Manager");
            }
            project.setManager(manager);
        }
        Project updatedProject = projectRepository.save(project);

        ProjectResponseDTO response = modelMapper.map(updatedProject, ProjectResponseDTO.class);
        response.setManagerId(updatedProject.getManager().getId());
        response.setManagerName(updatedProject.getManager().getName());

        return response;
    }

    @Override
    public void deleteProject(Long projectId) {
        Project project =projectRepository.findById(projectId).orElseThrow(() ->
                new ProjectNotFoundException("Project not found with id: " + projectId));

        if(project.getProjectStatus()==ProjectStatus.IN_PROGRESS){
            throw new ValidationException("Cannot delete project that is in progress");
        }
       if(projectAssignmentRepository.existsByProjectId(projectId)){
           throw new ValidationException("Remove assigned employees before deleting project");
        }
       projectRepository.delete(project);

    }
}
