package com.example.ecommerce.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Entity(tableName = "product")
public class Product {
    @NonNull
    @PrimaryKey
    private String id;
    private String name;
    private double price;
    private String image;
    private String description;


    public Product(String name, double price, String image, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
