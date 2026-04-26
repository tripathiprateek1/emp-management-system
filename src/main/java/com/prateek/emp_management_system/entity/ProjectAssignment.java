package com.prateek.emp_management_system.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
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
