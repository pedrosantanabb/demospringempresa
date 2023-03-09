package com.aurorastudio.demo.services;

import com.aurorastudio.demo.entities.PriceReduction;
import com.aurorastudio.demo.exceptions.PriceReductionDateErrorParameterException;
import com.aurorastudio.demo.exceptions.PriceReductionNotFoundException;
import com.aurorastudio.demo.repositories.ItemRepository;
import com.aurorastudio.demo.repositories.PriceReductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service
public class PriceReductionService {

    @Autowired
    PriceReductionRepository priceReductionRepository;
    @Autowired
    ItemRepository itemRepository;

    public List<PriceReduction> findAll() {
        return priceReductionRepository.findAll();
    }

    public PriceReduction getPriceReductionDetail(Long id){
        return priceReductionRepository.findById(id).orElseThrow(() -> new PriceReductionNotFoundException(id));
    }

    public PriceReduction createPriceReduction(PriceReduction priceReduction){
        boolean dateOverlaped = false;
        Set<PriceReduction> prices = itemRepository.findById(priceReduction.getItem().getId()).get().getPriceReductions();
        dateOverlaped = checkDateOverlaping(priceReduction, prices);

        if(dateOverlaped) {
            return priceReductionRepository.save(priceReduction);
        } else{
            ///TODO Lanzar excepción con fechas solapadas
            throw new PriceReductionDateErrorParameterException();
        }
    }

    public PriceReduction updatePriceReduction(PriceReduction priceReduction, Long id){
        boolean dateOverlaped = false;
        Set<PriceReduction> prices = priceReduction.getItem().getPriceReductions();
        dateOverlaped = checkDateOverlaping(priceReduction, prices);
        if(dateOverlaped){
            return priceReductionRepository.findById(id).map(priceReductionBD -> {
                priceReductionBD.setReducedPrice(priceReduction.getReducedPrice());
                priceReductionBD.setStartDate(priceReduction.getStartDate());
                priceReductionBD.setEndDate(priceReduction.getEndDate());
                priceReductionBD.setItem(priceReduction.getItem());
                return priceReductionRepository.save(priceReductionBD);
            }).orElseGet(()->{
                priceReduction.setId(id);
                return priceReductionRepository.save(priceReduction);
            });
        } else{
            throw new PriceReductionDateErrorParameterException();
        }

    }

    public void deleteById(Long id) {
        priceReductionRepository.deleteById(id);
    }

    private boolean checkDateOverlaping(PriceReduction newPriceReduction, Set<PriceReduction> listPrices){
        if(listPrices == null || listPrices.size() == 0) return true;

        for (PriceReduction priceOlder: listPrices ){
            if (newPriceReduction.getStartDate().after(priceOlder.getEndDate()) || newPriceReduction.getEndDate().before(priceOlder.getStartDate())) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


}
