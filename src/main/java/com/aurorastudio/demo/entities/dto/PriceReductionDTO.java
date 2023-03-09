package com.aurorastudio.demo.entities.dto;

import java.io.Serializable;
import java.util.Date;

public class PriceReductionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double reducedPrice;
    private Date startDate;
    private Date endDate;
    private ItemDTO item;

    public PriceReductionDTO(Double reducedPrice, Date startDate, Date endDate) {
        this.reducedPrice = reducedPrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PriceReductionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(Double reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }
}
