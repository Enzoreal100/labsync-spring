package com.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(mappedBy = "user")
    private int id;
    
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    @Column(nullable = false)
    private Position position;

    @Column(nullable = false, unique = true)
    private Timestamp creationDate = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn(name = "lab_id", referencedColumnName = "id")
    @Column(nullable = false)
    private int lab;

    @Column(nullable = false, unique = true)
    private String cardCode;

    public User(Timestamp creationDate, Position position, String name, int id, int lab, String cardCode)
    {
        this.id = id;
        this.lab = lab;
        this.creationDate = creationDate;
        this.position = position;
        this.name = name;
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

    public int getLab() {
        return lab;
    }

    public void setLab(int lab) {
        this.lab = lab;
    }

    public String getCardCode() { return cardCode; }

    public void setCardCode(String cardCode) { this.cardCode = cardCode; }
}