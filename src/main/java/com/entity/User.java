package com.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

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

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password_hash;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    public User(String name, Position position, Lab lab, String cardCode, String email, String password_hash, Timestamp createdAt) {
        this.name = name;
        this.position = position;
        this.lab = lab;
        this.cardCode = cardCode;
        this.email = email;
        this.password_hash = password_hash;
        this.createdAt = createdAt;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getCardCode() { return cardCode; }

    public void setCardCode(String cardCode) { this.cardCode = cardCode; }

}