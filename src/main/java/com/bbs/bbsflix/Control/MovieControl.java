package com.bbs.bbsflix.Control;

import com.bbs.bbsflix.model.ResultsEntity;
import com.bbs.bbsflix.service.MovieService;
import com.bbs.bbsflix.Control.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bbsflix")
public class MovieControl {

    private final MovieService movieService;
    private final Order orderService;

    @Autowired
    public MovieControl(MovieService movieService) {
        this.movieService = movieService;
        this.orderService = new Order(movieService); // Order sınıfını burada oluşturuyoruz.
    }

    @GetMapping("/allMovies")
    public List<ResultsEntity> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/movies/sort")
    public List<ResultsEntity> getSortedMovies(@RequestParam(value = "order", defaultValue = "asc") String order) {
        List<ResultsEntity> movies = movieService.getMovies(); // MovieService'den film listesini alıyoruz

        if (order.equalsIgnoreCase("asc")) {
            return orderService.orderByTitleAscending(movies);
        } else {
            return orderService.orderByTitleDescending(movies);
        }
    }
}
