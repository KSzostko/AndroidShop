package com.example.ecommerce;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductListViewModel extends ViewModel {
    // not sure if this is the way for lists
    private MutableLiveData<ProductAdapter> adapter = new MutableLiveData<>();

    public MutableLiveData<ProductAdapter> getAdapter() {
        return adapter;
    }

    public void setAdapter(ProductAdapter adapter) {
        this.adapter.setValue(adapter);
    }
}
