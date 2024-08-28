package com.bbs.bbsflix.service;

import com.bbs.bbsflix.model.MovieEntity;
import com.bbs.bbsflix.model.ResultsEntity;

import java.util.Comparator;
import java.util.List;

public class Order {


    public static List<ResultsEntity> orderByTitleAsc(List<ResultsEntity> movies) {
        return movies.stream()
                .sorted(Comparator.comparing(ResultsEntity::getTitle))
                .toList();
    }

    public static List<ResultsEntity> orderByTitleDesc(List<ResultsEntity> movies) {
        return movies.stream()
                .sorted(Comparator.comparing(ResultsEntity::getTitle).reversed())
                .toList();
    }

    public static List<ResultsEntity> orderByPopularity(List<ResultsEntity> movies) {
        return movies.stream()
                .sorted(Comparator.comparing(ResultsEntity::getPopularity).reversed())
                .toList();
    }

    public static List<ResultsEntity> orderByRatingAscending(List<ResultsEntity> movies){
        return movies.stream()
                .sorted(Comparator.comparing(ResultsEntity::getVote_average).reversed())
                .toList();
    }

    public static List<ResultsEntity> orderByRatingDescending(List<ResultsEntity> movies){
        return movies.stream()
                .sorted(Comparator.comparing(ResultsEntity::getVote_average))
                .toList();
    }

    public static List<ResultsEntity> orderByReleaseDateAscending(List<ResultsEntity> movies){
        return movies.stream()
                .sorted(Comparator.comparing(ResultsEntity::getRelease_date))
                .toList();
    }

    public static List<ResultsEntity> orderByReleaseDateDescending(List<ResultsEntity> movies){
        return movies.stream()
                .sorted(Comparator.comparing(ResultsEntity::getRelease_date).reversed())
                .toList();
    }

}
