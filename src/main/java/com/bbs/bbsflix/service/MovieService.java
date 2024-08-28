package com.bbs.bbsflix.service;

import com.bbs.bbsflix.model.MovieEntity;
import com.bbs.bbsflix.model.ResultsEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final RestTemplate restTemplate;
    private final ObjectMapper jacksonObjectMapper;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Autowired
    public MovieService(RestTemplate restTemplate, ObjectMapper jacksonObjectMapper) {
        this.restTemplate = restTemplate;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    public MovieEntity getMovies() throws IOException {

        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey + "&language=en-US&page=11";
        String response = restTemplate.getForObject(url, String.class);

        Map<String, Object> responseMap = jacksonObjectMapper.readValue(response, new TypeReference<Map<String, Object>>(){});
        List<Map<String, Object>> resultsList = (List<Map<String, Object>>) responseMap.get("results");

        List<ResultsEntity> movieList = new ArrayList<>();
        for (Map<String, Object> movieMap : resultsList) {
            ResultsEntity movie = jacksonObjectMapper.convertValue(movieMap, ResultsEntity.class);
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
    public ResultsEntity getMovieByTitle(String title) throws IOException {
        MovieEntity movieEntity = getMovies();
        return movieEntity.getResults().stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
    public List<ResultsEntity> filterMoviesByOriginalLanguage(List<ResultsEntity> movies, String language) {
        return movies.stream()
                .filter(movie -> movie.getOriginal_language().equalsIgnoreCase(language))
                .collect(Collectors.toList());
    }
}


/*
{2,3,4},{1,5,6}

 */