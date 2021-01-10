package com.example.ecommerce.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "order")
public class Order {
    @PrimaryKey
    private String id;
    private String status;

    public Order(String status) {
        this.id = UUID.randomUUID().toString();
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
