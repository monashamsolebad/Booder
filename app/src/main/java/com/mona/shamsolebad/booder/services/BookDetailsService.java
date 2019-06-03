package com.mona.shamsolebad.booder.services;

import android.util.Log;

import com.mona.shamsolebad.booder.helpers.XmlParser;
import com.mona.shamsolebad.booder.models.BookDetailsModel;
import com.mona.shamsolebad.booder.models.BookListModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class BookDetailsService {

    private static final String KEY_TITLE = "title";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "id";
    private static final String KEY_IMAGEURL = "image_url";
    private static final String KEY_BOOK = "book";
    private static final String KEY_DESC = "description";
    private static final String KEY_RATING = "average_rating";
    private static final String KEY_ISBN = "isbn";

    public BookDetailsService() {
    }

    public BookDetailsModel parseBookDetailsResponse(String response) {


        BookDetailsModel book;
        XmlParser parser = new XmlParser();

        Document doc = parser.getDomElement(response);

        NodeList nl = doc.getElementsByTagName(KEY_BOOK);

        Element e = (Element) nl.item(0);


        String id = parser.getValue(e, KEY_ID);
        String title = parser.getValue(e, KEY_TITLE);
        String authorName = parser.getValue(e, KEY_NAME);
        String isbn =  parser.getValue(e, KEY_ISBN);
        String description = parser.getValue(e, KEY_DESC);
        String ratingStr = parser.getValue(e, KEY_RATING);
        String imageUrl = parser.getValue(e, KEY_IMAGEURL);


       book= new BookDetailsModel(Integer.parseInt(id), title, authorName, Float.parseFloat(ratingStr), imageUrl, description, isbn);

        return book;
    }
}
