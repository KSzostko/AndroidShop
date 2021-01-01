package com.example.ecommerce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.dummy.DummyContent.DummyItem;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private final List<DummyItem> mValues;

    public ReviewAdapter(List<DummyItem> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_item_review, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView mReviewDate;
        private TextView mReviewDescription;
        private RatingBar mRating;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mReviewDate = view.findViewById(R.id.review_date);
            mReviewDescription = view.findViewById(R.id.review_description);
            mRating = view.findViewById(R.id.review_rating);

            mRating.setRating(4);

            // TODO: retrieve data form the db
        }
    }
}
