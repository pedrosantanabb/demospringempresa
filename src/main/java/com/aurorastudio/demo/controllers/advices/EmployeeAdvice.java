package com.aurorastudio.demo.controllers.advices;


import com.aurorastudio.demo.exceptions.EmployeeDataViolationException;
import com.aurorastudio.demo.exceptions.EmployeeNotFoundException;
import com.aurorastudio.demo.exceptions.EmployeeNullException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeAdvice {

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmployeeDataViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String employeeBadRequestHandler(EmployeeDataViolationException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmployeeNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String employeeNullRequestHandler(EmployeeNullException e){
        return e.getMessage();
    }
}
