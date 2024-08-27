package com.bbs.bbsflix.service;

import com.bbs.bbsflix.model.ResultsEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final RestTemplate restTemplate;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Autowired
    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ResultsEntity> getMovies() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey + "&language=en-US&page=1";
        String response = restTemplate.getForObject(url, String.class);

        JSONObject jsonResponse = new JSONObject(response);
        JSONArray resultsArray = jsonResponse.getJSONArray("results");

        List<ResultsEntity> movieList = new ArrayList<>();

        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject movieJson = resultsArray.getJSONObject(i);

            ResultsEntity movie = new ResultsEntity();
            movie.setAdult(movieJson.getBoolean("adult"));
            movie.setBackdrop_path(movieJson.getString("backdrop_path"));
            movie.setGenre_ids(convertJsonArrayToList(movieJson.getJSONArray("genre_ids")));
            movie.setId(movieJson.getInt("id"));
            movie.setOriginal_language(movieJson.getString("original_language"));
            movie.setOriginal_title(movieJson.getString("original_title"));
            movie.setPopularity(movieJson.getDouble("popularity"));
            movie.setPoster_path(movieJson.getString("poster_path"));
            movie.setRelease_date(movieJson.getString("release_date"));
            movie.setTitle(movieJson.getString("title"));
            movie.setVideo(movieJson.getBoolean("video"));
            movie.setVote_average(movieJson.getDouble("vote_average"));
            movie.setVote_count(movieJson.getInt("vote_count"));

            movieList.add(movie);
        }

        return movieList;
    }

    private List<Integer> convertJsonArrayToList(JSONArray jsonArray) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getInt(i));
        }
        return list;
    }
}
