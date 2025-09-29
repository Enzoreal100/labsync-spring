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
}
