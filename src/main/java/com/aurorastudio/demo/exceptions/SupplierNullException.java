package com.aurorastudio.demo.exceptions;

public class SupplierNullException extends RuntimeException{

    public SupplierNullException(){
        super("El supplier no puede ser null");
    }
}
