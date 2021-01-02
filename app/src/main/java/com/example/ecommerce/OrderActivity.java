package com.example.ecommerce;

import androidx.fragment.app.Fragment;

public class OrderActivity extends BottomNavActivity {

    @Override
    protected Fragment createFragment() {
        return OrderFragment.newInstance();
    }
}