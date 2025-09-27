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

    @OneToMany(mappedBy = "item")
    private List<Stock> stocks;
}
