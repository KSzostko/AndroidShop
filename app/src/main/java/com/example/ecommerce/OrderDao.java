package com.example.ecommerce;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Order order);

    @Update
    void update(Order order);

    @Delete
    void delete(Order order);

    @Query("SELECT * FROM `order`")
    LiveData<List<Order>> findAll();

    @Query("SELECT * FROM `order` WHERE status=:status")
    LiveData<List<Order>> findOrdersWithStatus(String status);
}
