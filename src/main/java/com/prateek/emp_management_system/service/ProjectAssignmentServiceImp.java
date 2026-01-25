package com.prateek.emp_management_system.service;

import com.prateek.emp_management_system.dto.CreateProjectRequestDTO;
import com.prateek.emp_management_system.dto.ProjectAssignmentRequestDTO;
import com.prateek.emp_management_system.dto.ProjectAssignmentResponseDTO;
import com.prateek.emp_management_system.dto.ProjectResponseDTO;
import com.prateek.emp_management_system.entity.Employee;
import com.prateek.emp_management_system.entity.Project;
import com.prateek.emp_management_system.entity.ProjectAssignment;
import com.prateek.emp_management_system.repository.EmployeeRepository;
import com.prateek.emp_management_system.repository.ProjectAssignmentRepository;
import com.prateek.emp_management_system.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;



public class ProjectAssignmentServiceImp implements ProjectAssignmentService{

    @Autowired
    private ProjectAssignmentRepository projectAssignmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProjectAssignmentResponseDTO assignEmployeeToProject(ProjectAssignmentRequestDTO dto) {
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new ProjectNotFoundException(
                        "Project not found with id: " + dto.getProjectId()));

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found with id: " + dto.getEmployeeId()));


        if(projectAssignmentRepository.existsByProjectIdAndEmployeeId(dto.getProjectId(),
                dto.getEmployeeId()))
        {
            throw new ValidationException("Employee already assigned to this project");
        }
        ProjectAssignment projectAssignment = new ProjectAssignment();
        projectAssignment.setProject(project);
        projectAssignment.setEmployee(employee);
        projectAssignment.setAssignedDate(LocalDate.now());
        ProjectAssignment saved = projectAssignmentRepository.save(projectAssignment);

        ProjectAssignmentResponseDTO responseDTO =
                modelMapper.map(saved, ProjectAssignmentResponseDTO.class);

        responseDTO.setProjectId(saved.getProject().getId());
        responseDTO.setEmployeeId(saved.getEmployee().getId());
        responseDTO.setProjectName(saved.getProject().getProjectName());
        responseDTO.setEmployeeName(saved.getEmployee().getName());
        return responseDTO;
    }

    @Override
    public List<ProjectAssignmentResponseDTO> getProjectsByEmployee(Long employeeId) {
        return List.of();
    }

    @Override
    public List<ProjectAssignmentResponseDTO> getEmployeesByProject(Long projectId) {
        return List.of();
    }
}
