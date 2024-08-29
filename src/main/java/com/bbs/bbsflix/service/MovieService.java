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

import javax.xml.transform.Result;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bbs.bbsflix.service.Order.*;

import java.util.stream.Collectors;


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

    public List<ResultsEntity> filterMoviesByTitle(List<ResultsEntity> movies, String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<ResultsEntity> orderMoviesByTitleAsc(List<ResultsEntity> movies) {
        return orderByTitleAsc(movies);

    }

    public List<ResultsEntity> orderMoviesByTitleDesc(List<ResultsEntity> movies) {
        return orderByTitleDesc(movies);
    }

    public List<ResultsEntity> orderByMoviePopularityAsc(List<ResultsEntity> movies) {
        return orderByPopularityAscending(movies);
    }

    public List<ResultsEntity> orderByMoviePopularityDesc(List<ResultsEntity> movies) {
        return Order.orderByPopularityDescending(movies);
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
    public List<ResultsEntity> filterMoviesByReleaseDate(List<ResultsEntity> movies, String releaseDate) {
        return movies.stream()
                .filter(movie -> movie.getRelease_date().equals(releaseDate))
                .collect(Collectors.toList());
    }
    public List<ResultsEntity> filterMoviesByFirstGenreId(List<ResultsEntity> movies, int genreId) {
        return movies.stream()
                .filter(movie -> !movie.getGenre_ids().isEmpty() && movie.getGenre_ids().get(0) == genreId)
                .collect(Collectors.toList());
    }
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

    public List<ResultsEntity> filterMoviesByOriginalLanguage(List<ResultsEntity> movies, String language){
        return Filters.filterMoviesByOriginalLanguage(movies, language);
    }

    public List<ResultsEntity> filterMoviesByReleaseDate(List<ResultsEntity> movies, String releaseDate){
        return Filters.filterMoviesByReleaseDate(movies, releaseDate);
    }

    public List<ResultsEntity> filterMoviesByFirstGenreId(List<ResultsEntity> movies, int firstGenre){
        return Filters.filterMoviesByFirstGenreId(movies, firstGenre);
    }

    public static List<ResultsEntity> orderMovies(List<ResultsEntity> movies, String sortBy, boolean ascending) {
        switch (sortBy) {
            case "title":
                return ascending ? orderByTitleAsc(movies) : orderByTitleDesc(movies);
            case "popularity":
                return ascending ? orderByPopularityAscending(movies) : orderByPopularityDescending(movies);
            case "rating":
                return ascending ? orderByRatingAscending(movies) : orderByRatingDescending(movies);
            case "releaseDate":
                return ascending ? orderByReleaseDateAscending(movies) : orderByReleaseDateDescending(movies);
            default:
                return movies;
        }
    }
}

