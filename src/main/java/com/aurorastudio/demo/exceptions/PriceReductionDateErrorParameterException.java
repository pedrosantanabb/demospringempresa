package com.aurorastudio.demo.exceptions;

public class PriceReductionDateErrorParameterException extends RuntimeException {

    public PriceReductionDateErrorParameterException(){
        super("Parameter error: Date overlaping ");
    }
}
