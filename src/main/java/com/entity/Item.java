package com.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "item")
public class Item {
    @Id
    private String eanCode;

    @Column
    private String name;

    @Column(nullable = true)
    private String imageUrl;

    @OneToMany(mappedBy = "item")
    private List<Stock> stocks;

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }    
}
