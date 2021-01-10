package com.example.ecommerce.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.UUID;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "orderItem",
        foreignKeys = {
            @ForeignKey(entity = Product.class,
                        parentColumns = "id",
                        childColumns = "productId",
                    onDelete = CASCADE),
            @ForeignKey(entity = Order.class,
                        parentColumns = "id",
                        childColumns = "orderId",
                        onDelete = CASCADE)
        })
public class OrderItem {
    @PrimaryKey
    private String id;
    private String productId;
    private String orderId;
    private int quantity;

    public OrderItem(String productId, String orderId, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
