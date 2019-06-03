package com.mona.shamsolebad.booder.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.mona.shamsolebad.booder.R;
import com.mona.shamsolebad.booder.adapters.SearchResultListAdapter;
import com.mona.shamsolebad.booder.helpers.RequestQueueProvider;
import com.mona.shamsolebad.booder.models.BookDetailsModel;
import com.mona.shamsolebad.booder.services.BookDetailsService;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    private TextView activity_details_cover_image_loading_text;
    private ImageView activity_details_cover_image;
    private TextView activity_details_title_text;
    private TextView activity_details_subtitle_text;
    private TextView activity_details_description_text;
    private Button activity_details_reviews_button;
    private BookDetailsModel bookDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        activity_details_cover_image_loading_text = findViewById(R.id.activity_details_cover_image_loading_text);
        activity_details_cover_image = findViewById(R.id.activity_details_cover_image);
        activity_details_title_text = findViewById(R.id.activity_details_title_text);
        activity_details_subtitle_text = findViewById(R.id.activity_details_subtitle_text);
        activity_details_description_text = findViewById(R.id.activity_details_description_text);
        activity_details_reviews_button = findViewById(R.id.activity_details_reviews_button);

        int bookId = getIntent().getIntExtra("bookId", 0);

        final BookDetailsService bookDetailsService = new BookDetailsService();
        String apiKey = this.getString(R.string.api_key);
        String bookUrl = this.getString(R.string.book_url);

        String url = String.format("%s?key=%s&id=%d", bookUrl, apiKey, bookId);

        RequestQueueProvider.getInstance(this).makeStringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                activity_details_cover_image_loading_text.setVisibility(View.GONE);
                activity_details_reviews_button.setVisibility(View.VISIBLE);

                bookDetails = bookDetailsService.parseBookDetailsResponse(response);

                activity_details_title_text.setText(bookDetails.getTitle());
                activity_details_subtitle_text.setText(bookDetails.getAuthor());
                activity_details_description_text.setText(Html.fromHtml(bookDetails.getDescription()));
                Picasso.get().load(bookDetails.getImageUrl()).into(activity_details_cover_image);

            }
        });

    }

    public void activity_details_reviews_button_click(View view) {

        Intent reviewsIntent = new Intent(this, ReviewsActivity.class);
        reviewsIntent.putExtra("bookId", bookDetails.getId());
        reviewsIntent.putExtra("isbn", bookDetails.getIsbn());
        reviewsIntent.putExtra("title", bookDetails.getTitle());
        startActivity(reviewsIntent);
    }
}
