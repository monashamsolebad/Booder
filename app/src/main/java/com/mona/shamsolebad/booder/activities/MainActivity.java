package com.mona.shamsolebad.booder.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.mona.shamsolebad.booder.R;
import com.mona.shamsolebad.booder.helpers.RequestQueueProvider;
import com.mona.shamsolebad.booder.models.BookListModel;
import com.mona.shamsolebad.booder.services.SearchService;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private EditText activity_main_search_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main_search_edit = findViewById(R.id.activity_main_search_edit);
    }

    public void activity_main_search_button_click(View view) {

        String query = activity_main_search_edit.getText().toString();
        Intent resultIntent = new Intent(this, ResultActivity.class);

        resultIntent.putExtra("query", query);
        startActivity(resultIntent);
    }
}
