package com.example.asus.tugas2_movie_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MainMovie> movie = new ArrayList<MainMovie>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().hasExtra("nama") && getIntent().hasExtra("rating") && getIntent().hasExtra("status")){
            ArrayList<String> nama = getIntent().getStringArrayListExtra("nama");
            ArrayList<String> rating = getIntent().getStringArrayListExtra("rating");
            ArrayList<String> status = getIntent().getStringArrayListExtra("status");
            for (int i = 0; i < nama.size(); i++) {
                Double temp;
                try {
                    temp = Double.parseDouble(rating.get(i));
                    if(temp > Double.parseDouble("10.0")){
                        temp = 10.0;
                    }
                    else if(temp < Double.parseDouble("0.0")){
                        temp = 0.0;
                    }
                } catch (Error e) {
                    temp = 0.0;
                }
                movie.add(new MainMovie(nama.get(i), temp, status.get(i)));
            }
            /*System.out.println("Error 3");*/
        }
        else{
            movie.add(new MainMovie("FANTASTIC BEAST : THE CRIMES OF GRINDELWALD", 0.0, "COMING SOON"));
            movie.add(new MainMovie("DEAR NATHAN : HALO SALMA", 0.0, "COMING SOON"));
            movie.add(new MainMovie("VENOM", 8.8, "NOW PLAYING"));
            movie.add(new MainMovie("FIRST MAN", 8.0, "NOW PLAYING"));
            movie.add(new MainMovie("GENERASI MICIN", 7.0, "NOW PLAYING"));

        }
        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.Recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, movie);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InputMovieToTheList.class);
                ArrayList<String> nama = new ArrayList<String>();
                ArrayList<String> rating = new ArrayList<String>();
                ArrayList<String> status = new ArrayList<String>();
                for(int i = 0; i < movie.size(); i++){
                    nama.add(movie.get(i).NamaFilm);
                    rating.add(String.valueOf(movie.get(i).RatingFilm));
                    status.add(movie.get(i).StatusFilm);
                }
                intent.putStringArrayListExtra("nama", nama);
                intent.putStringArrayListExtra("rating", rating);
                intent.putStringArrayListExtra("status", status);
                v.getContext().startActivity(intent);
            }
        });
    }
}
