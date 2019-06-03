package com.mona.shamsolebad.booder.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.mona.shamsolebad.booder.R;
import com.mona.shamsolebad.booder.adapters.OnViewHolderClickListenerDelegate;
import com.mona.shamsolebad.booder.adapters.SearchResultListAdapter;
import com.mona.shamsolebad.booder.helpers.RequestQueueProvider;
import com.mona.shamsolebad.booder.models.BookListModel;
import com.mona.shamsolebad.booder.services.SearchService;


import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity implements OnViewHolderClickListenerDelegate {

    private ArrayList<BookListModel> bookList;
    private TextView activity_result_search_text;
    private RecyclerView activity_result_recycler;
    private SearchResultListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        activity_result_search_text = findViewById(R.id.activity_result_search_text);
        activity_result_recycler = findViewById(R.id.activity_result_recycler);

        String query = getIntent().getStringExtra("query");

        activity_result_search_text.setText(query);

        final SearchService searchService = new SearchService();
        String apiKey = this.getString(R.string.api_key);
        String searchUrl = this.getString(R.string.search_url);

        String url = String.format("%s?key=%s&q=%s", searchUrl, apiKey, query);

        RequestQueueProvider.getInstance(this).makeStringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                bookList = searchService.parseSearchResponse(response);
                adapter = new SearchResultListAdapter(getBaseContext(), bookList, ResultActivity.this);


                activity_result_recycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                activity_result_recycler.addItemDecoration(new DividerItemDecoration(getBaseContext(), DividerItemDecoration.VERTICAL));
                activity_result_recycler.setAdapter(adapter);
            }
        });

    }


    @Override
    public void onClickViewHolder(View view, int position) {

        BookListModel book = bookList.get(position);

        Intent detailsIntent = new Intent(this, DetailsActivity.class);

        detailsIntent.putExtra("bookId", book.getId());
        startActivity(detailsIntent);
    }
}
