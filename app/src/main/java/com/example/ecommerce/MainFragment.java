package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerce.database.Product;
import com.example.ecommerce.database.ShopViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainFragment extends Fragment {
    ShopViewModel shopViewModel;
    List<Product> productsList;

    private CardView recommendedCard;
    private TextView recommendedName;
    private TextView recommendedPrice;
    private ImageView recommendedImageView;
    private RatingBar recommendedRating;

    private CardView bestsellerCard;
    private TextView bestsellerName;
    private TextView bestsellerPrice;
    private ImageView bestsellerImageView;
    private RatingBar bestsellerRating;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recommendedCard = view.findViewById(R.id.recommended_card);
        recommendedName = view.findViewById(R.id.recommended_name);
        recommendedPrice = view.findViewById(R.id.recommended_price);
        recommendedImageView = view.findViewById(R.id.recommended_image);
        recommendedRating = view.findViewById(R.id.recommended_rating);

        bestsellerCard = view.findViewById(R.id.bestseller_card);
        bestsellerName = view.findViewById(R.id.bestseller_name);
        bestsellerPrice = view.findViewById(R.id.bestseller_price);
        bestsellerImageView = view.findViewById(R.id.bestseller_image);
        bestsellerRating = view.findViewById(R.id.bestseller_rating);

        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.findAllProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                productsList = products;

                recommendedName.setText(products.get(0).getName());
                recommendedPrice.setText(getString(R.string.product_price, products.get(0).getPrice()));
                Picasso.get()
                        .load(products.get(0).getImage())
                        .into(recommendedImageView);

                bestsellerName.setText(products.get(1).getName());
                bestsellerPrice.setText(getString(R.string.product_price, products.get(1).getPrice()));
                Picasso.get()
                        .load(products.get(1).getImage())
                        .into(bestsellerImageView);

                recommendedCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                        intent.putExtra(ProductAdapter.PRODUCT_ID, products.get(0).getId());

                        startActivity(intent);
                    }
                });

                bestsellerCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                        intent.putExtra(ProductAdapter.PRODUCT_ID, products.get(1).getId());

                        startActivity(intent);
                    }
                });

                setRating(products.get(0), 0);
                setRating(products.get(1), 1);
            }
        });

        return view;
    }

    private void setRating(Product product, int n) {
        shopViewModel.findProductScore(product.getId()).observe(getViewLifecycleOwner(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                if(n == 0) {
                    if(aFloat == null) recommendedRating.setRating(0);
                    else recommendedRating.setRating(aFloat);
                } else {
                    if(aFloat == null) bestsellerRating.setRating(0);
                    else bestsellerRating.setRating(aFloat);
                }
            }
        });
    }
}
