package com.prateek.emp_management_system.dto;

import com.prateek.emp_management_system.entity.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;

@Data
public class CreateProjectRequestDTO {

    @NotBlank(message = "Project name is mandatory")
    private String projectName;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotNull(message = "Start date is mandatory")
    private LocalDate startDate;
    @NotNull(message = "End date is mandatory")
    private LocalDate endDate;
    @NotNull(message="Manager id is compulsory")
    private Long managerId;


}
