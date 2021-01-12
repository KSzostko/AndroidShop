package com.example.ecommerce;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {
    private MutableLiveData<OrderAdapter> adapter = new MutableLiveData<>();
    private MutableLiveData<Float> price = new MutableLiveData<>();

    public MutableLiveData<OrderAdapter> getAdapter() {
        return adapter;
    }

    public MutableLiveData<Float> getPrice() {
        return price;
    }

    public void setAdapter(OrderAdapter adapter) {
        this.adapter.setValue(adapter);
    }

    public void setPrice(float price) {
        this.price.setValue(price);
    }
}
