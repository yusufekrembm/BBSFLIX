package com.bbs.bbsflix.service;

import com.bbs.bbsflix.model.MovieEntity;
import com.bbs.bbsflix.model.ResultsEntity;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Filters {


    public static List<ResultsEntity> filterMoviesByOriginalLanguage(List<ResultsEntity> movies, String language) {
        return movies.stream()
                .filter(movie -> movie.getOriginal_language().equalsIgnoreCase(language))
                .collect(Collectors.toList());
    }
    public static List<ResultsEntity> filterMoviesByReleaseDate(List<ResultsEntity> movies, String releaseDate) {
        return movies.stream()
                .filter(movie -> movie.getRelease_date().equals(releaseDate))
                .collect(Collectors.toList());
    }
    public static List<ResultsEntity> filterMoviesByFirstGenreId(List<ResultsEntity> movies, int genreId) {
        return movies.stream()
                .filter(movie -> !movie.getGenre_ids().isEmpty() && movie.getGenre_ids().get(0) == genreId)
                .collect(Collectors.toList());
    }

}
