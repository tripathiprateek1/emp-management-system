package com.prateek.emp_management_system.exception;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String message) {
        super(message);
    }

}
