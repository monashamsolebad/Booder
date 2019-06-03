package com.mona.shamsolebad.booder.services;

import android.util.Log;

import com.mona.shamsolebad.booder.helpers.XmlParser;
import com.mona.shamsolebad.booder.models.BookListModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class SearchService {

    private static final String KEY_WORK = "work";
    private static final String KEY_RATING = "average_rating";
    private static final String KEY_TITLE = "title";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "id";
    private static final String KEY_IMAGEURL = "image_url";
    private static final String KEY_BOOK = "best_book";

    public SearchService() {
    }

    public ArrayList<BookListModel> parseSearchResponse(String response) {

        ArrayList<BookListModel> bookList = new ArrayList<>();
        XmlParser parser = new XmlParser();

        Document doc = parser.getDomElement(response);

        NodeList nl = doc.getElementsByTagName(KEY_WORK);

        for (int i = 0; i < nl.getLength(); i++) {

            Element e = (Element) nl.item(i);
            NodeList bookNode = e.getElementsByTagName(KEY_BOOK);

            String id = parser.getValue((Element) bookNode.item(0), KEY_ID);
            String title = parser.getValue(e, KEY_TITLE);
            String authorName = parser.getValue(e, KEY_NAME);
            String ratingStr = parser.getValue(e, KEY_RATING);
            String imageUrl = parser.getValue(e, KEY_IMAGEURL);


            BookListModel newBook = new BookListModel(Integer.parseInt(id), title, authorName, Float.parseFloat(ratingStr), imageUrl);
            bookList.add(newBook);
        }

        return bookList;
    }
}
