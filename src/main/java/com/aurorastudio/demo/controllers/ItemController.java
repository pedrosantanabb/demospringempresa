package com.aurorastudio.demo.controllers;

import com.aurorastudio.demo.entities.Item;
import com.aurorastudio.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemService service;

    @GetMapping("/items")
    public List<Item> all(){
        return service.findAll();
    }
    @GetMapping("/items/{id}")
    public Item getItemDetail(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("/items")
    public Item newItem(@RequestBody Item newItem){
        return service.createItem(newItem);
    }

    @PutMapping("/items/{id}")
    public Item udateItem(@RequestBody Item updatedItem, @PathVariable Long id){
        return service.updateItem(updatedItem, id);
    }

    @DeleteMapping("items/{id}")
    public void deleteItem(@PathVariable Long id){

        service.deleteItem(id);
    }

}
