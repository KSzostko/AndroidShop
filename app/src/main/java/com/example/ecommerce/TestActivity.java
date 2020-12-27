package com.example.ecommerce;

import androidx.fragment.app.Fragment;

public class TestActivity extends BottomNavActivity {

    @Override
    protected Fragment createFragment() {
        return new TestFragment();
    }
}