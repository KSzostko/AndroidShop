package com.example.ecommerce;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class ProductDetailsActivity extends BottomNavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return new ProductDetailsFragment();
    }
}