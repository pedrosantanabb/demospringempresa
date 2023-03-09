package com.aurorastudio.demo.exceptions;

public class SupplierDataViolationException extends RuntimeException {

    public SupplierDataViolationException(String field){
        super("El campo: " + field + ", no puede ser null o vacío");
    }
}
