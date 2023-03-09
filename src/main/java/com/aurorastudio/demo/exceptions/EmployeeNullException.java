package com.aurorastudio.demo.exceptions;

public class EmployeeNullException extends RuntimeException{

    public EmployeeNullException(){
        super("El empleado no puede ser null");
    }
}
