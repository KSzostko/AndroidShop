package com.example.ecommerce;

import androidx.fragment.app.Fragment;

public class ProductDetailsActivity extends BottomNavActivity {

    @Override
    protected Fragment createFragment() {
        return new ProductDetailsFragment();
    }
}