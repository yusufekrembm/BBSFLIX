package com.bbs.bbsflix.service;

import com.bbs.bbsflix.model.ResultsEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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

    public MovieEntity getMovies() throws IOException {

        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey + "&language=en-US&page=11";
        String response = restTemplate.getForObject(url, String.class);

        Map<String, Object> responseMap = objectMapper.readValue(response, new TypeReference<Map<String, Object>>(){});
        List<Map<String, Object>> resultsList = (List<Map<String, Object>>) responseMap.get("results");

        List<ResultsEntity> movieList = new ArrayList<>();
        for (Map<String, Object> movieMap : resultsList) {
            ResultsEntity movie = objectMapper.convertValue(movieMap, ResultsEntity.class);
            movieList.add(movie);
        }

        int page = (Integer) responseMap.get("page");
        int totalPages = (Integer) responseMap.get("total_pages");
        int totalResults = (Integer) responseMap.get("total_results");

        return new MovieEntity(page, movieList, totalPages, totalResults);
    }

    public List<ResultsEntity> orderMoviesByTitleAsc(List<ResultsEntity> movies) {
        return Order.orderByTitleAsc(movies);

    }

    public List<ResultsEntity> orderMoviesByTitleDesc(List<ResultsEntity> movies) {
        return Order.orderByTitleDesc(movies);
    }

    public List<ResultsEntity> orderByMoviePopularity(List<ResultsEntity> movies) {
        return Order.orderByPopularity(movies);
    }

    public List<ResultsEntity> orderByMovieRatingAsc(List<ResultsEntity> movies) {
        return Order.orderByRatingAscending(movies);
    }

    public List<ResultsEntity> orderByMovieRatingDesc(List<ResultsEntity> movies) {
        return Order.orderByRatingDescending(movies);
    }

    public List<ResultsEntity> orderByReleaseDateAsc(List<ResultsEntity> movies) {
        return Order.orderByReleaseDateAscending(movies);
    }

    public List<ResultsEntity> orderByReleaseDateDesc(List<ResultsEntity> movies) {
        return Order.orderByReleaseDateDescending(movies);
    }

}

