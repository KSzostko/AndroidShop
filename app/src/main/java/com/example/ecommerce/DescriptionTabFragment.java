package com.example.ecommerce;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DescriptionTabFragment extends Fragment {
    private TextView mDescriptionView;
    private String description;

    public DescriptionTabFragment(String description) {
        this.description = description;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description_tab, container, false);

        mDescriptionView = view.findViewById(R.id.details_description);
        mDescriptionView.setText(description);

        return view;
    }
}
