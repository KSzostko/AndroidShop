package com.example.ecommerce;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order")
public class Order {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String status;

    public Order(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
