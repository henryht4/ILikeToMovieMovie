package com.example.along.myfablix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        MovieResult movie = (MovieResult) getIntent().getSerializableExtra("Movie");


        TextView title = (TextView) findViewById(R.id.titleTextView);
        TextView year = (TextView) findViewById(R.id.yearTextView);
        TextView director = (TextView) findViewById(R.id.directorTextView);
        TextView rating = (TextView) findViewById(R.id.ratingTextView);
        ListView starName = (ListView) findViewById(R.id.starListView) ;
        ListView genre = (ListView) findViewById(R.id.genreListView) ;

        title.setText(movie.getTitle());
        year.setText(movie.getYear().toString());
        director.setText(movie.getDirector());
        rating.setText(movie.getRating().toString());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, movie.getStarName());
        starName.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, movie.getGenres());
        genre.setAdapter(adapter2);
    }
}
