package com.prateek.emp_management_system.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class ProjectAssignmentResponseDTO {

    private Long assignmentId;

    private Long employeeId;
    private String employeeName;

    private Long projectId;
    private String projectName;

    private LocalDate assignedDate;
}
