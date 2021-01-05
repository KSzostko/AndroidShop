package com.example.ecommerce;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ecommerce.dummy.DummyContent.DummyItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final List<DummyItem> mValues;

    public ProductAdapter(List<DummyItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).id);
        holder.mProductPriceView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mProductPriceView;
        public final RatingBar mProductRatingView;
        public final ImageView mProductImageView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mTitleView = (TextView) view.findViewById(R.id.product_title);
            mProductPriceView = (TextView) view.findViewById(R.id.product_price);
            mProductRatingView = (RatingBar) view.findViewById(R.id.product_rating);
            mProductRatingView.setRating(3.5f);

            mProductImageView = (ImageView) view.findViewById(R.id.product_image);
            String testImgUrl = "https://www.nvidia.com/content/dam/en-zz/Solutions/geforce/ampere/rtx-3090/geforce-rtx-3090-shop-300-t.png";
            // loading img
            Picasso.get()
                    .load(testImgUrl)
                    .into(mProductImageView);

            view.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mProductPriceView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);

            // TODO: put specific product data
            v.getContext().startActivity(intent);
        }
    }
}