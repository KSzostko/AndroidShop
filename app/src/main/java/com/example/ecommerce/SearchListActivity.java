package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class SearchListActivity extends BottomNavActivity {

    @Override
    protected Fragment createFragment() {
        return new ProductListFragment();
    }
}