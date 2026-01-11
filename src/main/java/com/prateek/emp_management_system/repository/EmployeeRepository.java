package com.prateek.emp_management_system.repository;


import com.prateek.emp_management_system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmail(String email);
    boolean existsByEmail(String email);
}
