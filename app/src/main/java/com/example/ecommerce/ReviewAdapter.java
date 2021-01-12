package com.example.ecommerce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.database.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<Review> mValues;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_item_review, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mValues != null) {
            Review review = mValues.get(position);
            holder.bind(review);
        }
    }

    @Override
    public int getItemCount() {
        if(mValues == null) return 0;
        
        return mValues.size();
    }

    public void setReviews(List<Review> reviews) {
        mValues = reviews;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Review review;
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
        }

        public void bind(Review review) {
            this.review = review;

            mReviewDate.setText(review.getDate());
            mReviewDescription.setText(review.getReviewText());
            mRating.setRating(review.getScore());
        }
    }
}
