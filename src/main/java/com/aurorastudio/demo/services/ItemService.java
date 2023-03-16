package com.aurorastudio.demo.services;


import com.aurorastudio.demo.entities.Item;
import com.aurorastudio.demo.exceptions.ItemNotFoundExecption;
import com.aurorastudio.demo.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository repository;

    public List<Item> findAll(){
        return repository.findAll();
    }

    public Item findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundExecption(id));
    }

    public Item createItem(Item newItem){
        if(newItem.getCreationDate()== null)
            newItem.setCreationDate(new Date());
        return repository.save(newItem);

    }

    public Item updateItem(Item updatedItem, Long id){
        return repository.findById(id).map(item ->{
            item.setName(updatedItem.getName());
            item.setState(updatedItem.getState());
            item.setPrice(updatedItem.getPrice());
            item.setDescription(updatedItem.getDescription());
            item.setCreator(updatedItem.getCreator());
            item.setSupplierList(updatedItem.getSupplierList());
            item.setPriceReductions(updatedItem.getPriceReductions());
            return repository.save(item);
        }).orElseGet(()->{
            updatedItem.setId(id);
            return repository.save(updatedItem);
        });
    }

    public void deleteItem(Long id){
        repository.deleteById(id);
    }
}
