package com.aurorastudio.demo.services;


import com.aurorastudio.demo.entities.Supplier;
import com.aurorastudio.demo.exceptions.SupplierDataViolationException;
import com.aurorastudio.demo.exceptions.SupplierNotFoundException;
import com.aurorastudio.demo.exceptions.SupplierNullException;
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
        if(id == null) throw new SupplierDataViolationException("id");
        return repository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));
    }


    public Supplier createSupplier(Supplier supplier){
        validateSupplier(supplier);
        return repository.save(supplier);
    }


    public Supplier updateSupplier(Supplier updatedSupplier, Long id){
        if(id == null) throw new SupplierDataViolationException("id");
        validateSupplier(updatedSupplier);

        return repository.findById(id).map(supplier ->{
            supplier.setCountry(updatedSupplier.getCountry());
            supplier.setName(updatedSupplier.getName());
            supplier.setItems(updatedSupplier.getItems());
            return repository.save(supplier);
        }).orElseGet(()->{
           throw new SupplierNotFoundException(id);
        });
    }

    public void deleteSupplier(Long id){
        repository.deleteById(id);
    }


    public void validateSupplier(Supplier supplier){
        if(supplier == null) throw new SupplierNullException();
        if(supplier.getName()== null || supplier.getCountry()==null) {
            throw new SupplierDataViolationException((supplier.getName()==null)?"name":"country");
        }
        if(supplier.getName().isEmpty() ||supplier.getCountry().isEmpty()){
            throw new SupplierDataViolationException((supplier.getName().isEmpty())?"name":"country");
        }
    }
}
