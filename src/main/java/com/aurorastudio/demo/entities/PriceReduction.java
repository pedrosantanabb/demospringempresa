package com.aurorastudio.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "price_reductions")
public class PriceReduction {
    private Long id;
    private Double reducedPrice;
    private Date startDate;
    private Date endDate;
    private Item item;

    public PriceReduction(Double reducedPrice, Date startDate, Date endDate) {
        this.reducedPrice = reducedPrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public PriceReduction() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="reduced_price", nullable = false)
    public Double getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(Double reducedPrice) {
        this.reducedPrice = reducedPrice;
    }
    @Column(name="start_date" , nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    @Column(name="end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="item_id")
    @JsonBackReference(value = "item_price")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "PriceReduction{" +
                "id=" + id +
                ", reducedPrice=" + reducedPrice +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", item=" + item +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceReduction that = (PriceReduction) o;
        return Objects.equals(id, that.id) && Objects.equals(reducedPrice, that.reducedPrice) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reducedPrice, startDate, endDate);
    }
}
