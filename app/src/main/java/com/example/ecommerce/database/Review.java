package com.example.ecommerce.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.File;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "review",
        foreignKeys = @ForeignKey(entity = Product.class,
                                  parentColumns = "id",
                                  childColumns = "productId",
                                  onDelete = CASCADE))
public class Review {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int productId;
    private float score;
    private String reviewText;
    // @TODO: image probably needs to be stored in some specific way in the db
    //private File image;
    private String date;

    public Review(int productId, float score, String reviewText, String date) {
        this.productId = productId;
        this.score = score;
        this.reviewText = reviewText;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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
