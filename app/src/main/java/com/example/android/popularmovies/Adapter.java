package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<MovieDetail> movie;
    private  Context context;


    Adapter(Context context, ArrayList<MovieDetail> movie){
        this.layoutInflater = LayoutInflater.from(context);
        this.movie=movie;
        this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout= layoutInflater.inflate(R.layout.list_item,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(layout);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final MovieDetail currentMovie = movie.get(i);


        viewHolder.cardView.setTag(i);
        viewHolder.title.setText(currentMovie.getmTitle());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+currentMovie.getmUrlImage()).into(viewHolder.image);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent movieIntent = new Intent(view.getContext(),Details.class);
                movieIntent.putExtra("Title" , currentMovie.getmTitle());
                movieIntent.putExtra("Description",currentMovie.getmDescription());
                movieIntent.putExtra("date",currentMovie.getmDate());
                movieIntent.putExtra("image",currentMovie.getmUrlImage());
                movieIntent.putExtra("rate", currentMovie.getmRate());
                view.getContext().startActivity(movieIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movie == null ? 0: movie.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardview);
            title=itemView.findViewById(R.id.title);
            image=itemView.findViewById(R.id.image);
        }
    }
}
