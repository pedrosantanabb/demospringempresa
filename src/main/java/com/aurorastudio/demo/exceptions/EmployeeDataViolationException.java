package com.aurorastudio.demo.exceptions;

public class EmployeeDataViolationException extends RuntimeException {

    public EmployeeDataViolationException(String field){
        super("El campo: " + field + ", no puede ser null o vacío");
    }
}
