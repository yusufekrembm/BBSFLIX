package com.bbs.bbsflix.Control;

import com.bbs.bbsflix.model.MovieEntity;
import com.bbs.bbsflix.model.ResultsEntity;
import com.bbs.bbsflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/bbsflix")

public class MovieControl {

    @Autowired private MovieService movieService;

    public MovieControl(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/allMovies")
    public List<ResultsEntity> getMovies() {
        return movieService.getMovies();
    }

}