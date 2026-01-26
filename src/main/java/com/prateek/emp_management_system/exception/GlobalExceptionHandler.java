package com.prateek.emp_management_system.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ValidationException.class)
     public ResponseEntity<ErrorResponse>handleValidationException(ValidationException ex){
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), 400),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateResourceException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), 409),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProjectNotFound(ProjectNotFoundException ex){
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND
        );
    }
}
