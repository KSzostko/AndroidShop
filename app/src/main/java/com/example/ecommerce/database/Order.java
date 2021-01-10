package com.example.ecommerce.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Entity(tableName = "order")
public class Order {
    @NonNull
    @PrimaryKey
    private String id;
    private String status;

    public Order(String status) {
        this.id = UUID.randomUUID().toString();
        this.status = status;
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
