package com.bbs.bbsflix.Control;

import com.bbs.bbsflix.model.MovieEntity;
import com.bbs.bbsflix.model.ResultsEntity;
import com.bbs.bbsflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/titleFilter")
    public ResponseEntity<ResultsEntity> getMovieByTitle(@RequestParam String title) {
        try {
            ResultsEntity movie = movieService.getMovieByTitle(title);
            if (movie != null) {
                return ResponseEntity.ok(movie);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/filterByOriginalLanguage")
    public ResponseEntity<List<ResultsEntity>> filterMoviesByOriginalLanguage(@RequestParam String language) {
        try {
            MovieEntity movieEntity = movieService.getMovies();
            List<ResultsEntity> filteredMovies = movieService.filterMoviesByOriginalLanguage(movieEntity.getResults(), language);
            if (!filteredMovies.isEmpty()) {
                return ResponseEntity.ok(filteredMovies);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/filterByReleaseDate")
    public ResponseEntity<List<ResultsEntity>> filterMoviesByReleaseDate(@RequestParam String releaseDate) {
        try {
            MovieEntity movieEntity = movieService.getMovies();
            List<ResultsEntity> filteredMovies = movieService.filterMoviesByReleaseDate(movieEntity.getResults(), releaseDate);
            if (!filteredMovies.isEmpty()) {
                return ResponseEntity.ok(filteredMovies);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/filterByFirstGenreId")
    public ResponseEntity<List<ResultsEntity>> filterMoviesByFirstGenreId(@RequestParam int genreId) {
        try {
            MovieEntity movieEntity = movieService.getMovies();
            List<ResultsEntity> filteredMovies = movieService.filterMoviesByFirstGenreId(movieEntity.getResults(), genreId);
            if (!filteredMovies.isEmpty()) {
                return ResponseEntity.ok(filteredMovies);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

