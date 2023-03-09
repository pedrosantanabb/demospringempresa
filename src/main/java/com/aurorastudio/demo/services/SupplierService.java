package com.aurorastudio.demo.services;


import com.aurorastudio.demo.entities.Supplier;
import com.aurorastudio.demo.exceptions.SupplierNotFoundException;
import com.aurorastudio.demo.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository repository;
    public List<Supplier> findAll() {
        return repository.findAll();
    }


    public Supplier getSupplierDetail( Long id){
        return repository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));
    }


    public Supplier createSupplier(Supplier supplier){
        Supplier response = repository.save(supplier);
        return response;
    }


    public Supplier updateSupplier(Supplier updatedSupplier, Long id){
        return repository.findById(id).map(supplier ->{
            supplier.setCountry(updatedSupplier.getCountry());
            supplier.setName(updatedSupplier.getName());
            supplier.setItems(updatedSupplier.getItems());
            return repository.save(supplier);
        }).orElseGet(()->{
            updatedSupplier.setId(id);
            return repository.save(updatedSupplier);
        });
    }

    public void deleteSupplier(Long id){
        repository.deleteById(id);
    }
}
