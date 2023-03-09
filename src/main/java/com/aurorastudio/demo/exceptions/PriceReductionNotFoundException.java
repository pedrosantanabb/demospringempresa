package com.aurorastudio.demo.exceptions;

public class PriceReductionNotFoundException extends RuntimeException{

    public PriceReductionNotFoundException(Long id){
        super("Could not find PriceReductinon with id: " + id);
    }
}
