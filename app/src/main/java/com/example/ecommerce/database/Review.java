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
    private int score;
    private String reviewText;
    // @TODO: image probably needs to be stored in some specific way in the db
    private File image;
    private Date date;

    public Review(int productId, int score, String reviewText, Date date) {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getDate() {
        return date;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
