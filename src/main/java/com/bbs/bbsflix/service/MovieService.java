package com.bbs.bbsflix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    private final RestTemplate restTemplate;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Autowired
    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getMovies() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey + "&language=en-US&page=1";
        return restTemplate.getForObject(url, String.class);
    }
}
