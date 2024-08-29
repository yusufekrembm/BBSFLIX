package com.bbs.bbsflix.Control;

import com.bbs.bbsflix.model.MovieEntity;
import com.bbs.bbsflix.model.ResultsEntity;
import com.bbs.bbsflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

    public MovieEntity getAllMovies() {
        try {
            return movieService.getMovies();
        } catch (IOException e) {
            e.printStackTrace();
            return new MovieEntity();
        }

    }

    @RequestMapping("/filterAndOrderMovies")
    public ResponseEntity<List<ResultsEntity>> filterMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String releaseDate,
            @RequestParam(required = false) Integer genreId,
            @RequestParam(required = false, defaultValue = "title") String sortBy,
            @RequestParam(required = false, defaultValue = "true") boolean ascending) {
        try {
            MovieEntity movieEntity = movieService.getMovies();
            List<ResultsEntity> filteredMovies = new ArrayList<>(movieEntity.getResults());
            if (title != null && !title.isEmpty()) {
                filteredMovies = movieService.filterMoviesByTitle(filteredMovies, title);
            }
            if (language != null && !language.isEmpty()) {
                filteredMovies = movieService.filterMoviesByOriginalLanguage(filteredMovies, language);
            }
            if (releaseDate != null && !releaseDate.isEmpty()) {
                filteredMovies = movieService.filterMoviesByReleaseDate(filteredMovies, releaseDate);
            }
            if (genreId != null) {
                filteredMovies = movieService.filterMoviesByFirstGenreId(filteredMovies, genreId);
            }

            filteredMovies = movieService.orderMovies(filteredMovies, sortBy, ascending);
            if (!filteredMovies.isEmpty()) {
                return ResponseEntity.ok(filteredMovies);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}


