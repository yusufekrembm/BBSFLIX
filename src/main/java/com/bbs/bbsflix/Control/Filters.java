package com.bbs.bbsflix.Control;

import java.util.List;

public class Filters {

    public String filterById(){
        return "doldurulucak";
    }

    public String filterByName(String title){
        return "doldurulucak";
    }

    public List filterByGenres(List<Integer> genre_ids){
        return List.of();
    }

    public List filterMovies(/* BİR PARAMETRE GELİCEK */){
        return List.of();
    }

    public List filterByLanguage(List<String> languages){
        return List.of();
    }


}
