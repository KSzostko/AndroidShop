package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BottomNavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch(item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(BottomNavActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        intent = new Intent(BottomNavActivity.this, ProductDetailsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_search:
                        Toast.makeText(BottomNavActivity.this, "Search", Toast.LENGTH_SHORT).show();
                        intent = new Intent(BottomNavActivity.this, SearchListActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_order:
                        Toast.makeText(BottomNavActivity.this, "Order", Toast.LENGTH_SHORT).show();
                        intent = new Intent(BottomNavActivity.this, OrderActivity.class);
                        startActivity(intent);
                        break;
                }

                return true;
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if(fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    protected abstract Fragment createFragment();
}