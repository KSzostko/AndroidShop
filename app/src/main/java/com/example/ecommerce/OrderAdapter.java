package com.example.ecommerce;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.database.Order;
import com.example.ecommerce.database.OrderItem;
import com.example.ecommerce.database.Product;
import com.example.ecommerce.database.ShopViewModel;
import com.example.ecommerce.dummy.DummyContent.DummyItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<OrderItem> mItems;
    private ShopViewModel shopViewModel;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_order, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mItems != null) {
            OrderItem item = mItems.get(position);

            shopViewModel = new ViewModelProvider((ViewModelStoreOwner) holder.itemView.getContext()).get(ShopViewModel.class);
            shopViewModel.findProduct(item.getProductId()).observe((LifecycleOwner) holder.itemView.getContext(), new Observer<Product>() {
                @Override
                public void onChanged(Product product) {
                    holder.bind(item, product);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(mItems == null) return 0;

        return mItems.size();
    }

    public void setItems(List<OrderItem> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView mOrderItemName;
        private TextView mOrderItemPrice;
        private EditText mOrderItemQuantity;
        private ImageView mOrderItemImage;
        private Button mAddButton;
        private Button mRemoveButton;
        private OrderItem orderItem;
        private Product product;

        // TODO: Calculate total price

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mOrderItemName = view.findViewById(R.id.orderitem_name);
            mOrderItemPrice = view.findViewById(R.id.orderitem_price);
            mOrderItemQuantity = view.findViewById(R.id.orderitem_quantity);
            mOrderItemImage = (ImageView) view.findViewById(R.id.orderitem_image);
            mAddButton = view.findViewById(R.id.orderitem_quantity_add);
            mRemoveButton = view.findViewById(R.id.orderitem_quantity_remove);

            mOrderItemQuantity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(actionId == EditorInfo.IME_ACTION_DONE) {
                        orderItem.setQuantity(Integer.parseInt(mOrderItemQuantity.getText().toString()));
                        shopViewModel.updateOrderItem(orderItem);

                        return true;
                    }

                    return false;
                }
            });

            mAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orderItem.setQuantity(orderItem.getQuantity() + 1);
                    shopViewModel.updateOrderItem(orderItem);
                }
            });

            mRemoveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(orderItem.getQuantity() != 1) {
                        orderItem.setQuantity(orderItem.getQuantity() - 1);
                        shopViewModel.updateOrderItem(orderItem);
                    } else {
                        shopViewModel.deleteOrderItem(orderItem);
                    }
                }
            });
        }

        public void bind(OrderItem item, Product product) {
            orderItem = item;
            this.product = product;

            mOrderItemName.setText(product.getName());
            mOrderItemPrice.setText(this.itemView.getContext().getResources().getString(R.string.orderitem_price, product.getPrice()));
            mOrderItemQuantity.setText(String.valueOf(item.getQuantity()));
            Picasso.get()
                    .load(product.getImage())
                    .into(mOrderItemImage);
        }
    }
}
