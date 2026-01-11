package com.prateek.emp_management_system.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectResponseDTO {

    private Long id;
    private String projectName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    // Manager basic info
    private Long managerId;
    private String managerName;
}
