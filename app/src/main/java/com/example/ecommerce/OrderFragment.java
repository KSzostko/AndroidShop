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

import com.example.ecommerce.database.OrderItem;
import com.example.ecommerce.database.ShopViewModel;
import com.example.ecommerce.dummy.DummyContent;

import java.util.List;

public class OrderFragment extends Fragment {
    private TextView price;
    private Button buyButton;
    private RecyclerView orderItems;
    private OrderAdapter adapter = new OrderAdapter();
    private ShopViewModel shopViewModel;

    public OrderFragment() {

    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        SharedPreferences preferences = getActivity().getSharedPreferences(BottomNavActivity.PREFERENCE_ORDER, Context.MODE_PRIVATE);
        int orderId = preferences.getInt(BottomNavActivity.CURRENT_ORDER, -1);
        Log.i("OrderFragment", String.valueOf(orderId));

        price = view.findViewById(R.id.order_sum);
        buyButton = view.findViewById(R.id.order_button);
        orderItems = view.findViewById(R.id.order_recyclerview);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Order realised! Thank you for your time.", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(BottomNavActivity.CURRENT_ORDER, -1);
                editor.apply();
            }
        });

        orderItems.setLayoutManager(new LinearLayoutManager(view.getContext()));
        orderItems.setAdapter(adapter);

        if(orderId != -1) {
            shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
            shopViewModel.findItemsForOrder(orderId).observe(getViewLifecycleOwner(), new Observer<List<OrderItem>>() {
                @Override
                public void onChanged(List<OrderItem> orderItems) {
                    adapter.setItems(orderItems);
                }
            });
        }

        return view;
    }
}
