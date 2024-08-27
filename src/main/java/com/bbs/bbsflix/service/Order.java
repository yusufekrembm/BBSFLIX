package com.bbs.bbsflix.service;

import com.bbs.bbsflix.model.MovieEntity;
import com.bbs.bbsflix.model.ResultsEntity;

import java.util.Comparator;
import java.util.List;

public class Order {

    public List<MovieEntity> orderByDurationAscending(){
        return List.of();
    }

    public List<MovieEntity> orderByDurationDescending(){
        return List.of();
    }

public static List<ResultsEntity> orderByTitleAsc(List<ResultsEntity> movies) {
    return movies.stream()
            .sorted(Comparator.comparing(ResultsEntity::getTitle))
            .toList();
}

    public List<MovieEntity> orderByTitleDescending(){
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
