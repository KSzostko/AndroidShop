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
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("DELETE FROM product")
    void deleteAll();

    @Query("SELECT * FROM product ORDER BY name")
    LiveData<List<Product>> findAll();

    @Query("SELECT  * FROM product WHERE name LIKE :name")
    LiveData<List<Product>> findProductWithName(String name);

    // @TODO: Filter by more parameters
}
