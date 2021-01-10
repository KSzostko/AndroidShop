package com.example.ecommerce.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderItem orderItem);

    @Update
    void update(OrderItem orderItem);

    @Delete
    void delete(OrderItem orderItem);

    @Query("DELETE FROM orderItem")
    void deleteAll();

    @Query("SELECT * FROM orderItem")
    LiveData<List<OrderItem>> findAll();

    @Query("SELECT * FROM orderItem WHERE productId=:productId")
    LiveData<List<OrderItem>> findOrderItemsForProduct(String productId);

    @Query("SELECT * FROM orderItem WHERE orderId=:orderId")
    LiveData<List<OrderItem>> findItemsForOrder(String orderId);
}
