package com.example.ecommerce;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<String> recommendedName = new MutableLiveData<>();
    private MutableLiveData<Float> recommendedPrice = new MutableLiveData<>();
    private MutableLiveData<String> recommendedImage = new MutableLiveData<>();
    private MutableLiveData<Float> recommendedRating = new MutableLiveData<>();

    private MutableLiveData<String> bestsellerName = new MutableLiveData<>();
    private MutableLiveData<Float> bestsellerPrice = new MutableLiveData<>();
    private MutableLiveData<String> bestsellerImage = new MutableLiveData<>();
    private MutableLiveData<Float> bestsellerRating = new MutableLiveData<>();

    public MutableLiveData<String> getRecommendedName() {
        return recommendedName;
    }

    public void setRecommendedName(String recommendedName) {
        this.recommendedName.setValue(recommendedName);
    }

    public MutableLiveData<Float> getRecommendedPrice() {
        return recommendedPrice;
    }

    public void setRecommendedPrice(float recommendedPrice) {
        this.recommendedPrice.setValue(recommendedPrice);
    }

    public MutableLiveData<String> getRecommendedImage() {
        return recommendedImage;
    }

    public void setRecommendedImage(String recommendedImage) {
        this.recommendedImage.setValue(recommendedImage);
    }

    public MutableLiveData<Float> getRecommendedRating() {
        return recommendedRating;
    }

    public void setRecommendedRating(float recommendedRating) {
        this.recommendedRating.setValue(recommendedRating);
    }

    public MutableLiveData<String> getBestsellerName() {
        return bestsellerName;
    }

    public void setBestsellerName(String bestsellerName) {
        this.bestsellerName.setValue(bestsellerName);
    }

    public MutableLiveData<Float> getBestsellerPrice() {
        return bestsellerPrice;
    }

    public void setBestsellerPrice(float bestsellerPrice) {
        this.bestsellerPrice.setValue(bestsellerPrice);
    }

    public MutableLiveData<String> getBestsellerImage() {
        return bestsellerImage;
    }

    public void setBestsellerImage(String bestsellerImage) {
        this.bestsellerImage.setValue(bestsellerImage);
    }

    public MutableLiveData<Float> getBestsellerRating() {
        return bestsellerRating;
    }

    public void setBestsellerRating(float bestsellerRating) {
        this.bestsellerRating.setValue(bestsellerRating);
    }
}
