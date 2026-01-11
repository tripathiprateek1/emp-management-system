package com.prateek.emp_management_system.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectAssignment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many assignments belong to one employee
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Many assignments belong to one project
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private LocalDate assignedDate;
}
