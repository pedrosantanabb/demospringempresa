package com.aurorastudio.demo.controllers;

import com.aurorastudio.demo.entities.PriceReduction;
import com.aurorastudio.demo.services.PriceReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
public class PriceReductionController {

    @Autowired
    PriceReductionService service;

    @GetMapping("/pricereductions")
    public List<PriceReduction> all(){
        List<PriceReduction> response = service.findAll();
        return response;
    }
    @GetMapping("/pricereductions/{id}")
    public PriceReduction getPriceReductionDetail(@PathVariable Long id){
        return service.getPriceReductionDetail(id);
    }

    @PostMapping("/pricereductions")
    public PriceReduction createPriceReduction(@RequestBody PriceReduction priceReduction){
        return service.createPriceReduction(priceReduction);
    }

    @PutMapping("/pricereductions/{id}")
    public PriceReduction updatePriceReduction(@RequestBody PriceReduction priceReduction, @PathVariable Long id){
        return service.updatePriceReduction(priceReduction, id);
    }

    @DeleteMapping("/pricereductions/{id}")
    public void deletePriceReduction(@PathVariable Long id){
        service.deleteById(id);
    }

}
