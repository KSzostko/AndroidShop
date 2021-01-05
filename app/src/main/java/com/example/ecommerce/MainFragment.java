package com.example.ecommerce;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class MainFragment extends Fragment {
    private ImageView recommendedImageView;
    private RatingBar recommendedRating;

    private ImageView bestsellerImageView;
    private RatingBar bestsellserRating;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recommendedImageView = view.findViewById(R.id.recommended_image);
        recommendedRating = view.findViewById(R.id.recommended_rating);

        bestsellerImageView = view.findViewById(R.id.bestseller_image);
        bestsellserRating = view.findViewById(R.id.bestseller_rating);

        recommendedRating.setRating(5);

        String testImgUrl = "https://www.nvidia.com/content/dam/en-zz/Solutions/geforce/ampere/rtx-3090/geforce-rtx-3090-shop-630-d@2x.png";
        // loading img
        Picasso.get()
                .load(testImgUrl)
                .into(recommendedImageView);

        bestsellserRating.setRating(4);
        Picasso.get()
                .load(testImgUrl)
                .into(bestsellerImageView);

        return view;
    }
}
