package com.example.ecommerce;

import androidx.fragment.app.Fragment;

public class MainActivity extends BottomNavActivity {

    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }
}