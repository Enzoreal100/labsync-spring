package com.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(nullable = false, unique = true)
    private Timestamp creationDate = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;

    @Column(nullable = false, unique = true)
    private String cardCode;

    public User(String name, Position position, Lab lab, String cardCode) {
        this.name = name;
        this.position = position;
        this.lab = lab;
        this.cardCode = cardCode;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public String getCardCode() { return cardCode; }

    public void setCardCode(String cardCode) { this.cardCode = cardCode; }

}