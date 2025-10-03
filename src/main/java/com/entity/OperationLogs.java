package com.entity;

import com.converter.ItemsLogListConverter;
import com.dto.ItemsLog;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "operation_logs")
public class OperationLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column
    private String operationType;

    @Column(columnDefinition = "CLOB")
    @Convert(converter = ItemsLogListConverter.class)
    private ArrayList<ItemsLog> item;

    public OperationLogs() {
    }

    public OperationLogs(User user, String operationType, ArrayList<ItemsLog> item) {
        this.user = user;
        this.operationType = operationType;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getOperationType() {
        return operationType;
    }

    public ArrayList<ItemsLog> getItem() {
        return item;
    }
}
