package com.aurorastudio.demo.entities.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SupplierDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String country;
    private Set<ItemDTO> items = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<ItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<ItemDTO> items) {
        this.items = items;
    }

    public void addItem(ItemDTO itemDTO){
        if(itemDTO != null){
            if(this.items == null){
                this.items = new HashSet<>();
            }
            itemDTO.addSupplier(this);
            this.items.add(itemDTO);
        }
    }
}
