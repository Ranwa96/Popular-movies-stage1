package com.example.android.popularmovies;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Details extends AppCompatActivity {
    ImageView imageView;
    TextView name;
    TextView release;
    TextView rate;
    TextView overview;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageView=(ImageView) findViewById(R.id.image_iv);
        name=(TextView) findViewById(R.id.name);
        release=(TextView) findViewById(R.id.release);
        rate=(TextView) findViewById(R.id.ratings);
        overview=(TextView) findViewById(R.id.overview);


        final String title = getIntent().getExtras().getString("Title");
        final String description = getIntent().getExtras().getString("Description");
        String date = getIntent().getExtras().getString("date");
        date = date.substring(0,4);
        final String image = getIntent().getExtras().getString("image");
        final float rating = getIntent().getExtras().getFloat("rate");
        name.setText(title);
        overview.setText(description);
        release.setText(date);
        rate.setText(rating+"");
        Picasso.with(this).load("https://image.tmdb.org/t/p/w500" +image).into(imageView);
        Log.i("rating", "onCreate: "+rating);
    }


}
