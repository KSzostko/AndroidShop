package com.example.ecommerce;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.database.Order;
import com.example.ecommerce.database.OrderItem;
import com.example.ecommerce.database.ShopViewModel;

import java.util.List;

public class OrderFragment extends Fragment {
    private TextView price;
    private Button buyButton;
    private RecyclerView orderItems;
    private OrderAdapter adapter = new OrderAdapter();
    private ShopViewModel shopViewModel;

    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);

        SharedPreferences preferences = getActivity().getSharedPreferences(ProductDetailsActivity.PREFERENCE_ORDER, Context.MODE_PRIVATE);
        String orderId = preferences.getString(ProductDetailsActivity.CURRENT_ORDER, "");

        price = view.findViewById(R.id.order_sum);
        buyButton = view.findViewById(R.id.order_button);
        orderItems = view.findViewById(R.id.order_recyclerview);

//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString(ProductDetailsActivity.CURRENT_ORDER, "");
//        editor.apply();

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!orderId.equals("")) {
                    Toast.makeText(getContext(), getString(R.string.order_realised), Toast.LENGTH_SHORT).show();

                    shopViewModel.findOrder(orderId).observe(getViewLifecycleOwner(), new Observer<Order>() {
                        boolean wasUpdated = false;

                        @Override
                        public void onChanged(Order order) {
                            if(!wasUpdated) {
                                order.setStatus("realised");
                                wasUpdated = true;
                            }
                        }
                    });

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(ProductDetailsActivity.CURRENT_ORDER, "");
                    editor.apply();
                } else {
                    Toast.makeText(getContext(), getString(R.string.no_products), Toast.LENGTH_SHORT).show();
                }
            }
        });

        orderItems.setLayoutManager(new LinearLayoutManager(view.getContext()));
        orderItems.setAdapter(adapter);

        if(!orderId.equals("")) {
            shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
            shopViewModel.findItemsForOrder(orderId).observe(getViewLifecycleOwner(), new Observer<List<OrderItem>>() {
                @Override
                public void onChanged(List<OrderItem> orderItems) {
                    adapter.setItems(orderItems);
                }
            });

            shopViewModel.orderTotalPrice(orderId).observe(getViewLifecycleOwner(), new Observer<Float>() {
                @Override
                public void onChanged(Float aFloat) {
                    if(aFloat == null) price.setText(getString(R.string.order_total, 0f));
                    else price.setText(getString(R.string.order_total, aFloat));
                }
            });
        } else {
            price.setText(getString(R.string.order_total, 0f));
        }

        return view;
    }
}
