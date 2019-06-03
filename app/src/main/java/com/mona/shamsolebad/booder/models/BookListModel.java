package com.mona.shamsolebad.booder.models;

public class BookListModel {

    private int id;
    private String title;
    private String author;
    private float rating;
    private String imageUrl;

    public BookListModel() {
    }

    public BookListModel(int id, String title, String author, float rating, String imageUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
