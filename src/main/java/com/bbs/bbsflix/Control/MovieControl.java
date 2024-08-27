package com.bbs.bbsflix.Control;

import com.bbs.bbsflix.model.MovieEntity;
import com.bbs.bbsflix.model.ResultsEntity;
import com.bbs.bbsflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/bbsflix")

public class MovieControl {

    @Autowired
    private MovieService movieService;

    public MovieControl(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/allMovies")
    public List<ResultsEntity> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/orderByTitleAsc")
    public List<ResultsEntity> orderMoviesByTitleAsc() {
        try {
            MovieEntity movieEntity = movieService.getMovies();
            return movieService.orderMoviesByTitleAsc(movieEntity.getResults());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of(); // Hata durumunda boş liste döndür
        }
    }

    @GetMapping("/orderByTitleDesc")
    public List<ResultsEntity> orderMoviesByTitleDesc() {
        try {
            MovieEntity movieEntity = movieService.getMovies();
            return movieService.orderMoviesByTitleDesc(movieEntity.getResults());
        } catch (IOException e) {
            return List.of();
        }
    }

    @GetMapping("/orderByPopularity")
    public List<ResultsEntity> orderMoviesByPopularity() {
        try {
            MovieEntity movieEntity = movieService.getMovies();
            return movieService.orderByMoviePopularity(movieEntity.getResults());
        } catch (IOException e) {
            return List.of();
        }



    }
}
