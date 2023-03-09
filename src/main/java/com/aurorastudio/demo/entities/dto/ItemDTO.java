package com.aurorastudio.demo.entities.dto;

import com.aurorastudio.demo.entities.Employee;
import com.aurorastudio.demo.entities.PriceReduction;
import com.aurorastudio.demo.entities.Supplier;
import com.aurorastudio.demo.globaldata.ItemStateEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String description;
    private String name;
    private Double price;
    private Date creationDate;
    private ItemStateEnum state;
    Set<PriceReductionDTO> priceReductions;
    Set<SupplierDTO> supplierList = new HashSet<>();
    private EmployeeDTO creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ItemStateEnum getState() {
        return state;
    }

    public void setState(ItemStateEnum state) {
        this.state = state;
    }

    public Set<PriceReductionDTO> getPriceReductions() {
        return priceReductions;
    }

    public void setPriceReductions(Set<PriceReductionDTO> priceReductions) {
        this.priceReductions = priceReductions;
    }

    public Set<SupplierDTO> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(Set<SupplierDTO> supplierList) {
        this.supplierList = supplierList;
    }

    public EmployeeDTO getCreator() {
        return creator;
    }

    public void setCreator(EmployeeDTO creator) {
        this.creator = creator;
    }
    
    public void addSupplier(SupplierDTO supplierDTO){
        if(supplierDTO!= null){
            if(this.supplierList == null){
                this.supplierList = new HashSet<>();
            }
            this.supplierList.add(supplierDTO);
        }
        
    }
}
