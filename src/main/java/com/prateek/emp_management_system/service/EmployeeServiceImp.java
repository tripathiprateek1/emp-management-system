package com.prateek.emp_management_system.service;

import com.prateek.emp_management_system.dto.EmployeeRequestDTO;
import com.prateek.emp_management_system.dto.EmployeeResponseDTO;
import com.prateek.emp_management_system.entity.Employee;
import com.prateek.emp_management_system.exception.DuplicateResourceException;
import com.prateek.emp_management_system.exception.EmployeeNotFoundException;
import com.prateek.emp_management_system.exception.ValidationException;
import com.prateek.emp_management_system.repository.EmployeeRepository;
import com.prateek.emp_management_system.repository.ProjectAssignmentRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectAssignmentRepository projectAssignmentRepository;
    @Autowired
    private ModelMapper   modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        Optional<Employee> employeeExist=employeeRepository.findByEmail(dto.getEmail());
        if(employeeExist.isPresent()) throw new DuplicateResourceException("Employee with email " +
                                                dto.getEmail() + " already exists");
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDesignation(dto.getDesignation());
        employee.setRole(dto.getRole());
        employee.setDateOfJoining(dto.getDateOfJoining());
        employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        employeeRepository.save(employee);


        return modelMapper.map(employee, EmployeeResponseDTO.class);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found with id: " + id));

        return modelMapper.map(employee,EmployeeResponseDTO.class);
    }

    @Override
    public Page<EmployeeResponseDTO> getAllEmployees(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        return employeePage.map(emp ->
                modelMapper.map(emp, EmployeeResponseDTO.class)
        );
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto) {
     Employee employee =employeeRepository.findById(id).orElseThrow
                                (() -> new EmployeeNotFoundException("Employee not found with id: " + id));
       employee = modelMapper.map(dto ,Employee.class);
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        Employee  updatedEmployee = employeeRepository.save(employee);
        return  modelMapper.map(updatedEmployee,EmployeeResponseDTO.class);
    }

    @Override
    public void deleteEmployee(Long id) {
          if(!employeeRepository.existsById(id)) {
              throw new EmployeeNotFoundException(
                      "Employee not found with id: " + id);
          }
          if(projectAssignmentRepository.existsByEmployeeId(id)){
              throw new ValidationException("Employee is assigned to a project. Remove assignment first.");
          }
          employeeRepository.deleteById(id);

    }
}

