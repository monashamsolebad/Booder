package com.mona.shamsolebad.booder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mona.shamsolebad.booder.R;
import com.mona.shamsolebad.booder.activities.DetailsActivity;
import com.mona.shamsolebad.booder.models.BookListModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchResultListAdapter extends RecyclerView.Adapter<SearchResultListAdapter.BookListViewHolder> {

    private ArrayList<BookListModel> bookList;
    private LayoutInflater mInflater;
    private Context context;
    private OnViewHolderClickListenerDelegate clickDelegate;

    public SearchResultListAdapter(Context context, ArrayList<BookListModel> bookList, OnViewHolderClickListenerDelegate delegate) {
        this.bookList = bookList;
        this.context = context;
        this.clickDelegate = delegate;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BookListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.activity_result_list_item, viewGroup, false);
        return new BookListViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder bookListViewHolder, int i) {
        BookListModel book = bookList.get(i);
        bookListViewHolder.bookTitleText.setText(book.getTitle());
        bookListViewHolder.bookSubTitleText.setText(book.getAuthor());
        Picasso.get().load(book.getImageUrl()).into(bookListViewHolder.bookImage);
        bookListViewHolder.bookRating.setRating(book.getRating());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView bookTitleText;
        final TextView bookSubTitleText;
        final ImageView bookImage;
        final RatingBar bookRating;
        final SearchResultListAdapter madapter;

        BookListViewHolder(@NonNull View itemView, SearchResultListAdapter adapter) {
            super(itemView);
            bookTitleText = itemView.findViewById(R.id.activity_result_list_item_title);
            bookSubTitleText = itemView.findViewById(R.id.activity_result_list_item_subtitle);
            bookImage = itemView.findViewById(R.id.activity_result_list_item_image);
            bookRating = itemView.findViewById(R.id.activity_result_list_item_rating);

            this.madapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
            clickDelegate.onClickViewHolder(v, mPosition);
        }
    }
}

