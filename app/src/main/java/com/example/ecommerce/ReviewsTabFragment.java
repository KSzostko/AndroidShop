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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.database.Review;
import com.example.ecommerce.database.ShopViewModel;
import com.example.ecommerce.dummy.DummyContent;

import java.util.List;

public class ReviewsTabFragment extends Fragment {
    private static final String ARG_PRODUCT_ID = "ARG_PRODUCT_ID";

    private ReviewAdapter adapter = new ReviewAdapter();
    private String productId;

    public ReviewsTabFragment() {
        // Required empty public constructor
    }

    public static ReviewsTabFragment newInstance(String productId) {
        ReviewsTabFragment fragment = new ReviewsTabFragment();

        Bundle args = new Bundle();
        args.putString(ARG_PRODUCT_ID, productId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            productId = getArguments().getString(ARG_PRODUCT_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews_tab, container, false);

        ShopViewModel shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);

        if(view instanceof RecyclerView) {
            Context context = view.getContext();

            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
        }

        shopViewModel.findReviewsForProduct(productId).observe(getViewLifecycleOwner(), new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                adapter.setReviews(reviews);
            }
        });

        return view;
    }
}
