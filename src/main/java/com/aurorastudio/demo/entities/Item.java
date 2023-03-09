package com.aurorastudio.demo.entities;

import com.aurorastudio.demo.globaldata.ItemStateEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item {
    private Long id;
    private String description;
    private String name;
    private Double price;
    private Date creationDate;
    private ItemStateEnum state;
    Set<PriceReduction> priceReductions;
    Set<Supplier> supplierList = new HashSet<>();
    private Employee creator;

    public Item(String name, String description, Double price, ItemStateEnum state) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.state = state;
        this.creationDate = new Date();
    }

    public Item() {
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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(nullable = false)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    @Column(name="create_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    @Enumerated(EnumType.ORDINAL)
    public ItemStateEnum getState() {
        return state;
    }

    public void setState(ItemStateEnum state) {
        this.state = state;
    }
    @OneToMany(mappedBy = "item")
    @JsonManagedReference(value = "item_price")
    public Set<PriceReduction> getPriceReductions() {
        return priceReductions;
    }

    public void setPriceReductions(Set<PriceReduction> priceReductions) {
        this.priceReductions = priceReductions;
    }
    @ManyToMany(cascade ={CascadeType.MERGE})
    @JsonIgnoreProperties("items")
    @JoinTable(name="items_suppliers",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    public Set<Supplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(Set<Supplier> supplierList) {
        this.supplierList = supplierList;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="creator_id")
    @JsonBackReference(value = "item_creator")
    public Employee getCreator() {
        return creator;
    }

    public void setCreator(Employee creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", creationDate=" + creationDate +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id) && Objects.equals(description, item.description) && Objects.equals(price, item.price) && creationDate.equals(item.creationDate) && state == item.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, creationDate, state);
    }

}
