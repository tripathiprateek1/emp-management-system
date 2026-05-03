package com.prateek.emp_management_system.service;

import com.prateek.emp_management_system.dto.CreateProjectRequestDTO;
import com.prateek.emp_management_system.dto.ProjectAssignmentRequestDTO;
import com.prateek.emp_management_system.dto.ProjectAssignmentResponseDTO;
import com.prateek.emp_management_system.dto.ProjectResponseDTO;
import com.prateek.emp_management_system.entity.Employee;
import com.prateek.emp_management_system.entity.Project;
import com.prateek.emp_management_system.entity.ProjectAssignment;
import com.prateek.emp_management_system.exception.EmployeeNotFoundException;
import com.prateek.emp_management_system.exception.ProjectNotFoundException;
import com.prateek.emp_management_system.exception.ValidationException;
import com.prateek.emp_management_system.repository.EmployeeRepository;
import com.prateek.emp_management_system.repository.ProjectAssignmentRepository;
import com.prateek.emp_management_system.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
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

        if (projectAssignmentRepository.existsByProjectAndEmployee(project, employee)) {
            throw new ValidationException("Employee already assigned to this project");
        }

        LocalDate assignedDate = LocalDate.now();

        if (assignedDate.isBefore(project.getStartDate()) ||
                assignedDate.isAfter(project.getEndDate())) {

            throw new ValidationException(
                    "Assignment date must be within project duration"
            );
        }

        ProjectAssignment assignment = new ProjectAssignment();
        assignment.setProject(project);
        assignment.setEmployee(employee);
        assignment.setAssignedDate(assignedDate);

        ProjectAssignment saved = projectAssignmentRepository.save(assignment);

        ProjectAssignmentResponseDTO response =
                modelMapper.map(saved, ProjectAssignmentResponseDTO.class);

        response.setProjectId(saved.getProject().getId());
        response.setEmployeeId(saved.getEmployee().getId());
        response.setProjectName(saved.getProject().getProjectName());
        response.setEmployeeName(saved.getEmployee().getName());

        return response;
    }
    @Override
    public List<ProjectAssignmentResponseDTO> getProjectsByEmployee(Long employeeId) {

        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException("Employee not found with id: " + employeeId);
        }

        if (!projectAssignmentRepository.existsByEmployeeId(employeeId)) {
            throw new ValidationException("Employee has no project assignments");
        }
        List<ProjectAssignment> listOfAssignment= projectAssignmentRepository.findByEmployeeId(employeeId);
        List<ProjectAssignmentResponseDTO> responseDTOList= listOfAssignment.stream().
                map(projectAssignment -> {
                    ProjectAssignmentResponseDTO responseDTO=modelMapper.map(projectAssignment,
                            ProjectAssignmentResponseDTO.class);
                    responseDTO.setEmployeeId(projectAssignment.getEmployee().getId());
                    responseDTO.setEmployeeName(projectAssignment.getEmployee().getName());
                    responseDTO.setProjectId(projectAssignment.getProject().getId());
                    responseDTO.setProjectName(projectAssignment.getProject().getProjectName());
                    return responseDTO;
                }).collect(Collectors.toList());
        return responseDTOList;
    }

    @Override
    public List<ProjectAssignmentResponseDTO> getEmployeesByProject(Long projectId) {
        if(!projectRepository.existsById(projectId)){
            throw new ProjectNotFoundException("Project not found with id: " + projectId);
        }
        if(projectAssignmentRepository.existsByProjectId(projectId)){
            throw new ValidationException("Project have no Assignment");
        }
        List<ProjectAssignment> listOfAssignment= projectAssignmentRepository.findByProjectId(projectId);

        List<ProjectAssignmentResponseDTO> responseDTOList= listOfAssignment.stream().
                map(projectAssignment -> {
                    ProjectAssignmentResponseDTO responseDTO=modelMapper.map(projectAssignment,
                            ProjectAssignmentResponseDTO.class);
                    responseDTO.setEmployeeId(projectAssignment.getEmployee().getId());
                    responseDTO.setEmployeeName(projectAssignment.getEmployee().getName());
                    responseDTO.setProjectId(projectAssignment.getProject().getId());
                    responseDTO.setProjectName(projectAssignment.getProject().getProjectName());
                    return responseDTO;
                }).collect(Collectors.toList());
        return responseDTOList;
    }
}
