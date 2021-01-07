package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ecommerce.database.Product;
import com.example.ecommerce.dummy.DummyContent.DummyItem;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    public static final String PRODUCT_ID = "com.example.ecommerce.PRODUCT_ID";

    private List<Product> mProducts;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mProducts != null) {
            Product product = mProducts.get(position);
            holder.bind(product);
        } else {
            Log.i("ProductAdapter", "No products available");
        }
    }

    @Override
    public int getItemCount() {
        if(mProducts == null) return 0;

        return mProducts.size();
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mProductPriceView;
        public final RatingBar mProductRatingView;
        public final ImageView mProductImageView;
        public Product product;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mTitleView = (TextView) view.findViewById(R.id.product_title);
            mProductPriceView = (TextView) view.findViewById(R.id.product_price);
            mProductRatingView = (RatingBar) view.findViewById(R.id.product_rating);
            mProductImageView = (ImageView) view.findViewById(R.id.product_image);

            view.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mProductPriceView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
            intent.putExtra(PRODUCT_ID, product.getId());

            // TODO: put specific product data
            v.getContext().startActivity(intent);
        }

        public void bind(Product product) {
            this.product = product;

            mTitleView.setText(product.getName());
            mProductPriceView.setText(String.valueOf(product.getPrice()));
            // TODO: Calculate product rating
            mProductRatingView.setRating(3.5f);

            Picasso.get()
                    .load(product.getImage())
                    .resize(300, 200)
                    .into(mProductImageView);
        }
    }
}