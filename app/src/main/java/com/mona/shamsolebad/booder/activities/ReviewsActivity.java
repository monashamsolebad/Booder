package com.mona.shamsolebad.booder.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.mona.shamsolebad.booder.R;

public class ReviewsActivity extends AppCompatActivity {

    private WebView webView;
    private String bookId;
    private String isbn;
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        try {
            webView = findViewById(R.id.activity_reviews_web);

            bookId = String.valueOf(getIntent().getExtras().getInt("bookId"));
            isbn = getIntent().getExtras().getString("isbn");
            title = getIntent().getExtras().getString("title");

            final String mimeType = "text/html";
            final String encoding = "UTF-8";
            String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                    "  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\n" +
                    "  <title></title>\n" +
                    "  <meta name=\"Generator\" content=\"Cocoa HTML Writer\">\n" +
                    "  <meta name=\"CocoaVersion\" content=\"1671.5\">\n" +
                    "  <style> #goodreads-widget { font-family: georgia, serif; padding: 18px 0; width:100%; } #goodreads-widget h1 { font-weight:normal; font-size: 16px; border-bottom: 1px solid #BBB596; margin-bottom: 0; } #goodreads-widget a { text-decoration: none; color:#660; } iframe{ background-color: #fff; width:100%!important; height:800px!important; } #goodreads-widget a:hover { text-decoration: underline; } #goodreads-widget a:active { color:#660; } #gr_footer { width: 100%; border-top: 1px solid #BBB596; text-align: right; } #goodreads-widget .gr_branding{ color: #382110; font-size: 11px; text-decoration: none; font-family: \"Helvetica Neue\", Helvetica, Arial, sans-serif; } </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "  \n" +
                    "  <div id=\"goodreads-widget\"> <div id=\"gr_header\"><h1><a rel=\"nofollow\" href=\"https://www.goodreads.com/book/show/HOLDER_BOOKID\">HOLDER_BOOKTITLE</a></h1></div> <iframe id=\"the_iframe\" src=\"https://www.goodreads.com/api/reviews_widget_iframe?did=HOLDER_DEVELOPERID&amp;format=html&amp;isbn=HOLDER_ISBN&amp;links=660&amp;min_rating=&amp;review_back=fff&amp;stars=000&amp;text=000\" width=\"545\" height=\"300\" frameborder=\"0\"></iframe> <div id=\"gr_footer\"> <a class=\"gr_branding\" target=\"_blank\" rel=\"nofollow noopener noreferrer\" href=\"https://www.goodreads.com/book/show/HOLDER_BOOKID?utm_medium=api&amp;utm_source=reviews_widget\">Reviews from Goodreads.com</a> </div> </div>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n";

            html = html.replace("HOLDER_BOOKID", bookId).replace("HOLDER_BOOKTITLE", title).replace("HOLDER_ISBN",isbn).replace("HOLDER_DEVELOPERID", this.getString(R.string.api_key));

            webView.loadDataWithBaseURL("", html, mimeType, encoding, "");
        }
        catch (Exception e){
            Log.d("error",e.getMessage());
        }
    }
}
