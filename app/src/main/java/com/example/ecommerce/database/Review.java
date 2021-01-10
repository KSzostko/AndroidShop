package com.example.ecommerce.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "review",
        foreignKeys = @ForeignKey(entity = Product.class,
                                  parentColumns = "id",
                                  childColumns = "productId",
                                  onDelete = CASCADE))
public class Review {
    @NonNull
    @PrimaryKey
    private String id;
    private String productId;
    private float score;
    private String reviewText;
    // @TODO: image probably needs to be stored in some specific way in the db
    //private File image;
    private String date;

    public Review(String productId, float score, String reviewText, String date) {
        this.id = UUID.randomUUID().toString();
        this.productId = productId;
        this.score = score;
        this.reviewText = reviewText;
        this.date = date;
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /*public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }*/
}
