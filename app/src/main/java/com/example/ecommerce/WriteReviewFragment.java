package com.example.ecommerce;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerce.database.Review;
import com.example.ecommerce.database.ShopViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteReviewFragment extends Fragment {
    private static final String ARG_PRODUCT_ID = "ARG_PRODUCT_ID";

    private EditText descriptionView;
    private RatingBar ratingBar;
    private Button submitButton;

    private String productId;
    private String descriptionText;
    private float currRating;

    public WriteReviewFragment() {
        // Required empty public constructor
    }

    public static WriteReviewFragment newInstance(String productId) {
        WriteReviewFragment fragment = new WriteReviewFragment();

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
        View view = inflater.inflate(R.layout.fragment_write_review_tab, container, false);

        descriptionView = view.findViewById(R.id.write_review_text);
        ratingBar = view.findViewById(R.id.write_review_rating);
        submitButton = view.findViewById(R.id.write_review_submit);

        descriptionView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                descriptionText = s.toString();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                currRating = rating;
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                // TODO: Date displays incorrectly
                DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
                Review review = new Review(productId, currRating, descriptionText, dateFormat.format(date));

                ShopViewModel shopViewModel = new ViewModelProvider(getActivity()).get(ShopViewModel.class);
                shopViewModel.insertReview(review);

                Toast.makeText(getContext(), getString(R.string.review_added), Toast.LENGTH_SHORT).show();
                descriptionView.setText("");
                ratingBar.setRating(0);
            }
        });

        return view;
    }
}
