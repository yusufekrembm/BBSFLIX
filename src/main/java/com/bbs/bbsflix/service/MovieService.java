package com.bbs.bbsflix.service;

import com.bbs.bbsflix.model.MovieEntity;
import com.bbs.bbsflix.model.ResultsEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Autowired
    public MovieService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public MovieEntity getMovies() throws IOException {

        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey + "&language=en-US&page=10";
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
}