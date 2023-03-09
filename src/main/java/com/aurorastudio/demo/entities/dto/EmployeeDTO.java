package com.aurorastudio.demo.entities.dto;

import com.aurorastudio.demo.entities.Item;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String role;
    private Set<ItemDTO> items = new HashSet();

    public EmployeeDTO(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public EmployeeDTO() {
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
            itemDTO.setCreator(this);
            this.items.add(itemDTO);
        }
    }
}
