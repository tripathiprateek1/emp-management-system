package com.prateek.emp_management_system.repository;


import com.prateek.emp_management_system.entity.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectAssignmentRepository  extends JpaRepository<ProjectAssignment,Long> {

    List<ProjectAssignment> findByEmployeeId(Long employeeId);
    List<ProjectAssignment> findByProjectId(Long projectId);
    boolean existsByEmployeeId(Long employeeId);
}
