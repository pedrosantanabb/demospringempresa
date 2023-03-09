package com.aurorastudio.demo.controllers;

import com.aurorastudio.demo.entities.Supplier;
import com.aurorastudio.demo.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService service;

    @GetMapping("/suppliers")
    public List<Supplier> all(){
        return service.findAll();
    }

    @GetMapping("/suppliers/{id}")
    public Supplier getSupplierDetail(@PathVariable Long id){
        return service.getSupplierDetail(id);
    }

    @PostMapping(value="/suppliers", consumes={"application/json"}, produces = {"application/json"})
    public Supplier createSupplier(@RequestBody Supplier supplier){
        return service.createSupplier(supplier);
    }

    @PutMapping("/suppliers/{id}")
    public Supplier updateSupplier(@RequestBody Supplier updatedSupplier, @PathVariable Long id){
        return service.updateSupplier(updatedSupplier, id);
    }

    @DeleteMapping("/suppliers/{id}")
    public void deleteSupplier(@PathVariable Long id){
        service.deleteSupplier(id);
    }
}
