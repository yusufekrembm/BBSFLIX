package com.bbs.bbsflix.service;

import com.bbs.bbsflix.model.MovieEntity;
import com.bbs.bbsflix.model.ResultsEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Autowired
    public MovieService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public MovieEntity getMovies() throws IOException {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey + "&language=en-US&page=1";
        String response = restTemplate.getForObject(url, String.class);

        // JSON yanıtını işleyin
        Map<String, Object> responseMap = objectMapper.readValue(response, new TypeReference<Map<String, Object>>(){});

        // MovieEntity oluşturun
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setPage((Integer) responseMap.get("page"));
        movieEntity.setTotal_pages((Integer) responseMap.get("total_pages"));
        movieEntity.setTotal_results((Integer) responseMap.get("total_results"));

        List<Map<String, Object>> resultsList = (List<Map<String, Object>>) responseMap.get("results");
        List<ResultsEntity> resultsEntityList = objectMapper.convertValue(resultsList, new TypeReference<List<ResultsEntity>>(){});

        movieEntity.setResults(resultsEntityList);

        return movieEntity;
    }
}
