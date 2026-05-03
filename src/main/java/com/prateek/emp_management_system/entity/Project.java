package com.prateek.emp_management_system.entity;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String projectName;

    private String description;

    private LocalDate startDate;
    private LocalDate endDate;

    // Many projects have one manager
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    // One project has many assignments
    @OneToMany(mappedBy = "project")
    private List<ProjectAssignment> assignments;


}
