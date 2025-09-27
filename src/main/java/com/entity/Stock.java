package com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "eanCode_item")
    private Item item;

    @Column
    private int quantity;

    @Column
    private int minQuantity;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;

    public Stock(int id, Item item, int quantity, int minQuantity, Lab lab) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
        this.lab = lab;
    }

    public Stock() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }
}
