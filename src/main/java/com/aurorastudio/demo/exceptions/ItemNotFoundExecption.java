package com.aurorastudio.demo.exceptions;

public class ItemNotFoundExecption extends RuntimeException{

    public ItemNotFoundExecption(Long id){
        super("Could not find item with id: " + id);
    }
}
