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
    private static final String DESCRIPTION_TEXT = "DESC";

    private TextView mDescriptionView;
    private String description;

    public DescriptionTabFragment() {
        // Required empty public constructor
    }

    public static DescriptionTabFragment newInstance(String description) {
        DescriptionTabFragment fragment = new DescriptionTabFragment();

        Bundle args = new Bundle();
        args.putString(DESCRIPTION_TEXT, description);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            description = getArguments().getString(DESCRIPTION_TEXT);
        }
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
