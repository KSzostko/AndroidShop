package com.example.ecommerce;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.database.Order;
import com.example.ecommerce.database.OrderItem;
import com.example.ecommerce.database.Product;
import com.example.ecommerce.database.ShopViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProductDetailsFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String PRODUCT_ID = "PRODUCT_ID";

    private ShopViewModel shopViewModel;
    private Product mProduct;
    private String productId;

    private ImageView mProductImageView;
    private TextView mProductNameView;
    private TextView mProductPriceView;
    private RatingBar mRatingBar;
    private Spinner mCurrencySpinner;
    private TabAdapter mAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mBuyButtonView;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProductDetailsFragment newInstance(String param1, String param2) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        if(savedInstanceState == null) {
            productId = requireActivity().getIntent().getStringExtra(ProductAdapter.PRODUCT_ID);
        } else {
            productId = savedInstanceState.getString(PRODUCT_ID);
        }

        mProductImageView = view.findViewById(R.id.details_image);
        mProductNameView = view.findViewById(R.id.details_name);
        mProductPriceView = view.findViewById(R.id.details_price);
        mRatingBar = view.findViewById(R.id.details_rating);
        mCurrencySpinner = view.findViewById(R.id.currency_spinner);
        mBuyButtonView = view.findViewById(R.id.fab_buy);

        mViewPager = view.findViewById(R.id.details_pager);
        mTabLayout = view.findViewById(R.id.details_tabs);

        mAdapter = new TabAdapter(getActivity().getSupportFragmentManager());

        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);

        getProductData();

        getScore();

        if(mCurrencySpinner != null) {
            mCurrencySpinner.setOnItemSelectedListener(this);
        }

        // TODO: Maybe try to style it
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_labels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(mCurrencySpinner != null) {
            mCurrencySpinner.setAdapter(adapter);
        }

        mBuyButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This will put product on your list", Toast.LENGTH_SHORT).show();

                SharedPreferences preferences = getActivity().getSharedPreferences(ProductDetailsActivity.PREFERENCE_ORDER, Context.MODE_PRIVATE);

                String orderId = preferences.getString(ProductDetailsActivity.CURRENT_ORDER, "");
                if(orderId.equals("")) {
                    Order order = new Order("pending");
                    shopViewModel.insertOrder(order);

                    OrderItem item = new OrderItem(productId, order.getId(), 1);
                    shopViewModel.insertOrderItem(item);

                    SharedPreferences.Editor prefEditor = preferences.edit();
                    prefEditor.putString(ProductDetailsActivity.CURRENT_ORDER, order.getId());
                    prefEditor.apply();
                } else {
                    // this changes quantity too much
                    shopViewModel.findProductInOrder(productId, orderId).observe(getViewLifecycleOwner(), new Observer<OrderItem>() {
                        boolean wasUpdated = false;
                        @Override
                        public void onChanged(OrderItem item) {
                            // otherwise this gets called multiple times
                            if(item != null) {
                                if(!wasUpdated) {
                                    item.setQuantity(item.getQuantity() + 1);
                                    shopViewModel.updateOrderItem(item);
                                    wasUpdated = true;
                                }
                            } else {
                                if(!wasUpdated) {
                                    OrderItem orderItem = new OrderItem(productId, orderId, 1);
                                    shopViewModel.insertOrderItem(orderItem);
                                    wasUpdated = true;
                                }
                            }
                        }
                    });
                }
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String currency = parent.getItemAtPosition(position).toString();

        if(currency.equals("PLN")) {
            if(mProduct != null) mProductPriceView.setText(Double.toString(mProduct.getPrice()));
        } else {
            convertPrice(currency);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void convertPrice(String currency) {
        String url = "https://free.currconv.com/api/v7/convert?apiKey=API_KEY&q=PLN_" + currency + "&compact=ultra";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // TODO: display snackbar with error message
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String resp = response.body().string();

                try {
                    JSONObject object = new JSONObject(resp);
                    String val = object.getString("PLN_" + currency);

                    double scale = Math.pow(10, 2);
                    double convertedPrice = Double.parseDouble(val) * mProduct.getPrice();
                    double roundedPrice = Math.round(convertedPrice * scale) / scale;

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProductPriceView.setText(Double.toString(roundedPrice));
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getProductData() {
        shopViewModel.findProduct(productId).observe(getViewLifecycleOwner(), new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                mProduct = product;

                mProductNameView.setText(mProduct.getName());

                mProductPriceView.setText(Double.toString(mProduct.getPrice()));

                Picasso.get()
                        .load(mProduct.getImage())
                        .into(mProductImageView);

                mAdapter.addFragment(ReviewsTabFragment.newInstance(productId), getString(R.string.reviews_tab));
                mAdapter.addFragment(DescriptionTabFragment.newInstance(mProduct.getDescription()), getString(R.string.description_tab));
                mAdapter.addFragment(WriteReviewFragment.newInstance(productId), getString(R.string.your_review_tab));

                mViewPager.setAdapter(mAdapter);
                mTabLayout.setupWithViewPager(mViewPager);
            }
        });
    }

    private void getScore() {
        shopViewModel.findProductScore(productId).observe(getViewLifecycleOwner(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                if(aFloat == null) mRatingBar.setRating(0);
                else mRatingBar.setRating(aFloat);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(PRODUCT_ID, productId);

        super.onSaveInstanceState(outState);
    }
}