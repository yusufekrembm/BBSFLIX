package com.bbs.bbsflix.Control;

import com.bbs.bbsflix.model.MovieEntity;
import com.bbs.bbsflix.model.ResultsEntity;
import com.bbs.bbsflix.service.MovieService;

import java.util.List;

public class Order {

    MovieService movieService;
    List<ResultsEntity> MovieList = movieService.getMovies();

    // Selection Sort for Title Ascending (A to Z)
    public List<ResultsEntity> orderByTitleAscending() {
        List<ResultsEntity> movies = MovieList;

        for (int i = 0; i < movies.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < movies.size(); j++) {
                if (movies.get(j).getTitle().compareToIgnoreCase(movies.get(minIndex).getTitle()) < 0) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            if (minIndex != i) {
                ResultsEntity temp = movies.get(i);
                movies.set(i, movies.get(minIndex));
                movies.set(minIndex, temp);
            }
        }
        return movies;
    }



    // Selection Sort for Title Descending (Z to A)
    public List<ResultsEntity> orderByTitleDescending() {
        List<ResultsEntity> movies = MovieList;

        for (int i = 0; i < movies.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < movies.size(); j++) {
                if (movies.get(j).getTitle().compareToIgnoreCase(movies.get(maxIndex).getTitle()) > 0) {
                    maxIndex = j;
                }
            }
            // Swap the found maximum element with the first element
            if (maxIndex != i) {
                ResultsEntity temp = movies.get(i);
                movies.set(i, movies.get(maxIndex));
                movies.set(maxIndex, temp);
            }
        }
        return movies;
    }

    public List<MovieEntity> orderByDurationAscending(){
        return List.of();
    }

    public List<MovieEntity> orderByDurationDescending(){
        return List.of();
    }

    public List<MovieEntity> orderByGenreAscending(){
        return List.of();
    }

    public List<MovieEntity> orderByGenreDescending(){
        return List.of();
    }

    public List<MovieEntity> orderByPopularity() { return List.of(); }

    public List<MovieEntity> orderByRatingAscending(){
        return List.of();
    }

    public List<MovieEntity> orderByRatingDescending(){
        return List.of();
    }

    public List<MovieEntity> orderByReleaseDateAscending(){
        return List.of();
    }

    public List<MovieEntity> orderByReleaseDateDescending(){
        return List.of();
    }




}
