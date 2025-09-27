package com.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "operation_logs")
public class OperationLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String operationType;

    @Column
    private ArrayList<Item> items;
}
