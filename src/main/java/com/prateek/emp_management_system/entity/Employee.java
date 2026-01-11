package com.prateek.emp_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String designation;

    private LocalDate dateOfJoining;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "manager")
    private List<Project> managedProjects;


}
