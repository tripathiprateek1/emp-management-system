package com.prateek.emp_management_system.dto;

import com.prateek.emp_management_system.entity.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateProjectRequestDTO {
    @NotBlank(message = "Project name is mandatory")
    private String projectName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long managerId;
    private ProjectStatus projectStatus;
}
