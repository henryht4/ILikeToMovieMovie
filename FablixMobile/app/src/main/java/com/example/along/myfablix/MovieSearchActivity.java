package com.example.along.myfablix;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MovieSearchActivity extends AppCompatActivity {

    private List<Movie> movieList;
    private MovieListAdapter movieListAdapter;

    private EditText titleEditText;
    private ListView movieListView;

    private SearchMovie mAuthTask = null;
    private GetMovie mGetMovieTask = null;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);
        titleEditText = (EditText) findViewById(R.id.movieTitleEditText);
        movieListView = (ListView) findViewById(R.id.moviesListView);
        movieList = new ArrayList<>();

        movieListAdapter = new MovieListAdapter(this, R.layout.movie_item, movieList);
        movieListView.setAdapter(movieListAdapter);
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textview= (TextView) ((LinearLayout)view).findViewById(R.id.titleTextView);
                String title = textview.getText().toString();
                mGetMovieTask = new GetMovie(title);
                mGetMovieTask.execute((Void) null);




            }
        });
    }

    public void searchMovies(View view) {

        movieList.clear();
        movieListAdapter.notifyDataSetChanged();

        String title = titleEditText.getText().toString();

        mAuthTask = new SearchMovie(title);
        mAuthTask.execute((Void) null);

    }

    public class SearchMovie extends AsyncTask<Void, Void, ArrayList<Movie>> {

        private final String mTitle;

        SearchMovie(String title) {
            mTitle = title;
        }

        @Override
        protected ArrayList<Movie> doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            StringBuilder sb = new StringBuilder();
            JSONArray json = null;

            try {
                String query = URLEncoder.encode(mTitle, "utf-8");
                URL url = new URL("http://10.0.2.2:8080/cart/AdvanceSearch?term=" + query);
                // Simulate network access.
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.connect();

                int status = urlConnection.getResponseCode();


                switch (status) {
                    case 200:
                    case 201:

                        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        br.close();
                        String result = sb.toString();
                        json = new JSONArray(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            ArrayList<Movie> result = new ArrayList<Movie>();
            buildList(json, result);
            return result;
        }


        private void buildList(JSONArray json, ArrayList<Movie> moviesList) {
            String id;
            String title;
            try {
                for (int i = 0; i < json.length(); i++) {
                    JSONObject row = json.getJSONObject(i);
                    id = row.getString("id");
                    title = row.getString("title");
                    moviesList.add(new Movie(id, title));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    @Override
    protected void onPostExecute(ArrayList<Movie> moviesList) {
        mAuthTask = null;
        int totalMovies = moviesList.size();
        if (totalMovies != 0) {
            for (int i = 0; i < totalMovies; i++) {
                movieListAdapter.add(moviesList.get(i));
            }
        } else
            Toast.makeText(context, "No movie match search key", Toast.LENGTH_LONG).show();

    }

}
    public class GetMovie extends AsyncTask<Void, Void, MovieResult> {

        private final String mTitle;

        GetMovie(String title) {
            mTitle = title;
        }

        @Override
        protected MovieResult doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            StringBuilder sb = new StringBuilder();
            JSONArray json = null;

            try {
                String query = URLEncoder.encode(mTitle, "utf-8");
                URL url = new URL("http://10.0.2.2:8080/cart/GetMovieByTitle?term=" + query);
                // Simulate network access.
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.connect();

                int status = urlConnection.getResponseCode();


                switch (status) {
                    case 200:
                    case 201:

                        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        br.close();
                        String result = sb.toString();
                        json = new JSONArray(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            MovieResult result = null;
            String id;
            String title;
            Integer year;
            String director;
            Double rating;
            ArrayList<String> starName= new ArrayList<String>();
            ArrayList<String> genres= new ArrayList<String>();
            try {
                for (int i = 0; i < json.length(); i++) {
                    JSONObject row = json.getJSONObject(i);
                    id = row.getString("id");
                    title = row.getString("title");
                    year = row.getInt("year");
                    director = row.getString("director");
                    rating = row.getDouble("rating");
                    JSONArray starArray = row.getJSONArray("starName");
                    for (int j = 0; j < starArray.length(); j++) {
                        starName.add(starArray.getString(j));
                    }


                    JSONArray genereArray = row.getJSONArray("genres");
                    for (int j = 0; j < genereArray.length(); j++) {
                        genres.add(genereArray.getString(j));
                    }

                    result = new MovieResult(id, title, year, director, rating);
                    result.setGenres(genres);
                    result.setStarName(starName);



                }} catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }





        @Override
        protected void onPostExecute(MovieResult movie) {
            Intent intent = new Intent(getApplicationContext(), MovieActivity.class);
            intent.putExtra("Movie",movie);
            startActivity(intent);
        }

    }

}
