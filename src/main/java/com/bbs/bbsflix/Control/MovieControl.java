package com.bbs.bbsflix.Control;

import com.bbs.bbsflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/bbsflix")

public class MovieControl {

    @Autowired private MovieService movieService;

    public MovieControl(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/allMovies")
    public String getMovies() {
        return movieService.getMovies();
    }




}
