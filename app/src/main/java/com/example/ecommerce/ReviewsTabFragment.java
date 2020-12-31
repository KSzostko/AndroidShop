package com.example.ecommerce;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.dummy.DummyContent;

public class ReviewsTabFragment extends Fragment {
    // TODO: change this to recycler view
    private TextView textView;

    public ReviewsTabFragment() {

    }

    public static ReviewsTabFragment newInstance() {
        return new ReviewsTabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews_tab, container, false);

        //textView = view.findViewById(R.id.details_reviews);

        if(view instanceof RecyclerView) {
            Context context = view.getContext();

            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new ReviewAdapter(DummyContent.ITEMS));
        }

        return view;
    }
}
