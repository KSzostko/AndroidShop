package com.example.ecommerce.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface OrderItemProductDao {
    @Query("SELECT SUM(o.quantity * p.price) FROM product p, orderItem o WHERE o.productId = p.id AND o.orderId=:orderId")
    LiveData<Float> orderTotalPrice(String orderId);
}
