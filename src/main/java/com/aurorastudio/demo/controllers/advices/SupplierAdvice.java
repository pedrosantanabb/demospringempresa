package com.aurorastudio.demo.controllers.advices;

import com.aurorastudio.demo.exceptions.SupplierNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SupplierAdvice {

    @ResponseBody
    @ExceptionHandler(SupplierNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String priceReductionNotFoundHandler(SupplierNotFoundException e) {
        return e.getMessage();
    }
}
