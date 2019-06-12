package com.example.android.popularmovies;

public class MovieDetail {
    private String Title;
    private String Image;
    private float Rate;
    private String Overview;
    private String Date;

    public MovieDetail(String Title, String Image, float Rate,String Overview,String Date) {
        this.Title = Title;
        this.Image = Image;
        this.Rate =Rate;
        this.Overview=Overview;
        this.Date = Date;
    }

    public String getmTitle() {
        return Title;
    }

    public String getmUrlImage() {
        return Image;
    }

    public float getmRate() {
        return Rate;
    }

    public String getmDescription() {
        return Overview;
    }

    public String getmDate() {
        return Date;
    }
}


