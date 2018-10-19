package com.example.asus.tugas2_movie_list;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class InputMovieToTheList extends AppCompatActivity {
    ArrayList<String> namafilm;
    ArrayList<String> ratingfilm;
    ArrayList<String> statusfilm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        addItemsOnSpinner2();
    }

    public void addItemsOnSpinner2() {

        Spinner moviespinner = (Spinner) findViewById(R.id.moviespinner);
        List<String> list = new ArrayList<String>();
        list.add("NOW PLAYING");
        list.add("COMING SOON");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        moviespinner.setAdapter(dataAdapter);
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add(View view) {
        Double temp;
        String rating, nama, status;
        EditText edt;
        namafilm = getIntent().getStringArrayListExtra("nama");
        ratingfilm = getIntent().getStringArrayListExtra("rating");
        statusfilm = getIntent().getStringArrayListExtra("status");
        try{
            edt = findViewById(R.id.nama_film);
            nama = edt.getText().toString();
            edt = findViewById(R.id.rating_film);
            rating = edt.getText().toString();
            Spinner spn = findViewById(R.id.moviespinner);
            status = String.valueOf(spn.getSelectedItem());
        }
        catch(Error e){
            nama = "";
            rating = "0";
            status = "";
        }
        if(nama != "" && status != "") {
            namafilm.add(nama);
            ratingfilm.add(rating);
            statusfilm.add(status);
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putStringArrayListExtra("nama",namafilm);
        intent.putStringArrayListExtra("rating",ratingfilm);
        intent.putStringArrayListExtra("status",statusfilm);
        view.getContext().startActivity(intent);
    }
}
