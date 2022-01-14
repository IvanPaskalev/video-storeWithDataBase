package com.company;

public class Movie {
    private int index;
    private String title;
    private int year;
    private String genre;
    private String description;
    boolean isRented;

    public Movie() {
    }

    public Movie(int index, String title, int year, String genre) {
        this.index = index;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.description = "Enter description";
        this.isRented = isRented();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    @Override
    public String toString() {
        return index +
                " " + title +
                " " + year +
                " " + genre +
                " " + description +
                " " + isRented;
    }
}
