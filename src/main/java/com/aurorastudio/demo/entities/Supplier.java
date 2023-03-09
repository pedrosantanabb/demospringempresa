package com.aurorastudio.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier {

    private Long id;
    private String name;
    private String country;
    private Set<Item> items = new HashSet<>();

    public Supplier(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Supplier() {
    }

    public void addItem(Item newItem){
        this.items.add(newItem);
        newItem.getSupplierList().add(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @ManyToMany(mappedBy = "supplierList")
    @JsonIgnoreProperties("supplierList")
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return id.equals(supplier.id) && name.equals(supplier.name) && country.equals(supplier.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", items=" + items +
                '}';
    }
}
