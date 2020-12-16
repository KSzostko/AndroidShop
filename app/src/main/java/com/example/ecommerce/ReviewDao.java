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
public interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Review review);

    @Update
    void update(Review review);

    @Delete
    void delete(Review review);

    @Query("SELECT * FROM review")
    LiveData<List<Review>> findAll();

    @Query("SELECT * FROM review WHERE productId=:productId")
    LiveData<List<Review>> findReviewsForProduct(int productId);

    @Query("SELECT * FROM review WHERE score=:score")
    LiveData<List<Review>> findReviewsWithScore(int score);
}
