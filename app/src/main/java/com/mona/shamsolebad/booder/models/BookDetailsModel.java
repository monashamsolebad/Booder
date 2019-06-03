package com.mona.shamsolebad.booder.models;

public class BookDetailsModel  {

    private int id;
    private String title;
    private String author;
    private float rating;
    private String imageUrl;
    private String description;
    private String isbn;

    public BookDetailsModel(int id, String title, String author, float rating, String imageUrl, String description, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.description = description;
        this.isbn = isbn;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
