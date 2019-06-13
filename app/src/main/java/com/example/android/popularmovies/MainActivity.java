package com.example.android.popularmovies;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieDetail>> {

    private String url = "https://api.themoviedb.org/3/movie/";
    RecyclerView recyclerView;
    Adapter adapter;
    SharedPreferences preferences;
    String sort_type;
    String path = "popular";
    private Menu menu;
    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        preferences = getSharedPreferences("popular_movies",MODE_PRIVATE);
        sort_type = preferences.getString("sort_type","popular");
        if (sort_type.equals("popular")){
            getSupportActionBar().setTitle("Popular Movies");
        }else if (sort_type.equals("top_rated")){
            getSupportActionBar().setTitle("Top Rated Movies");
            path = "top_rated";
        }


// Loader
        if (isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(0, null, this).forceLoad();

        }

    }




    private boolean isConnected() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network

            return connected = true;
        } else

            return connected = false;
    }

    @Override
    public Loader<ArrayList<MovieDetail>> onCreateLoader(int i, @Nullable Bundle bundle) {
        // int page = bundle.getInt()
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String section = sharedPreferences.getString(getString(R.string.settings), "popular");

        Uri baseUri = Uri.parse(url);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendPath(section);
        uriBuilder.appendQueryParameter("api_key", "c1e72fc9716bf1438d70d5a9478e0398");
        Log.i("myUrl", "onCreateLoader: " + uriBuilder.toString());
        return new MovieAsyncTask(MainActivity.this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MovieDetail>> loader, ArrayList<MovieDetail> movieDetails) {
        if (movieDetails.isEmpty()) {
            Log.i("content", "onLoadFinished: no content");
            Toast.makeText(this, "No contents", Toast.LENGTH_SHORT).show();
        } else {
            adapter = new Adapter(MainActivity.this, movieDetails);
            recyclerView.setAdapter(adapter);

        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MovieDetail>> loader) {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        

      
        return true;
    }
}


