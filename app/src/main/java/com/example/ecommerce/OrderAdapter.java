package com.example.ecommerce;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
            Log.i("OrderAdapter", item.getProductId());

            ShopViewModel shopViewModel = new ViewModelProvider((ViewModelStoreOwner) holder.itemView.getContext()).get(ShopViewModel.class);
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
        private EditText mOrderItemQuantity;
        private ImageView mOrderItemImage;
        private OrderItem orderItem;
        private Product product;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mOrderItemName = view.findViewById(R.id.orderitem_name);
            mOrderItemQuantity = view.findViewById(R.id.orderitem_quantity);
            mOrderItemImage = (ImageView) view.findViewById(R.id.orderitem_image);
        }

        public void bind(OrderItem item, Product product) {
            orderItem = item;
            this.product = product;

            mOrderItemName.setText(product.getName());
            mOrderItemQuantity.setText(item.getQuantity());
            Picasso.get()
                    .load(product.getImage())
                    .into(mOrderItemImage);
        }
    }
}
