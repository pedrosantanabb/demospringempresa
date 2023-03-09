package com.aurorastudio.demo.controllers.advices;

import com.aurorastudio.demo.exceptions.PriceReductionDateErrorParameterException;
import com.aurorastudio.demo.exceptions.PriceReductionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PriceReductionAdvice {

    @ResponseBody
    @ExceptionHandler(PriceReductionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String priceReductionNotFoundHandler(PriceReductionNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PriceReductionDateErrorParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String priceReductionParameterWrongHandler(PriceReductionDateErrorParameterException e) {
        return e.getMessage();
    }
}
