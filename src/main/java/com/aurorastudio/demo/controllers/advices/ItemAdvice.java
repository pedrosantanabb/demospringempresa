package com.aurorastudio.demo.controllers.advices;

import com.aurorastudio.demo.exceptions.ItemNotFoundExecption;
import com.aurorastudio.demo.exceptions.PriceReductionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ItemAdvice {

    @ResponseBody
    @ExceptionHandler(ItemNotFoundExecption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String priceReductionNotFoundHandler(ItemNotFoundExecption e) {
        return e.getMessage();
    }
}
