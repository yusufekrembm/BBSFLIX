package com.bbs.bbsflix.sorting;

import com.bbs.bbsflix.Entity.ResultsEntity;

import java.util.Comparator;
import java.util.List;

public class Order {
//a dan z ye artan sıralama
public static Comparator<ResultsEntity> sortByTitleAsc = new Comparator<ResultsEntity>(){
    @Override
    public int compare(ResultsEntity o1, ResultsEntity o2) {
        return o1.getTitle().compareToIgnoreCase(o2.getTitle());
    }

};
// a dan z ye azalan sıralma
    public static Comparator<ResultsEntity> sortByTitleDesc = new Comparator<ResultsEntity>(){
        @Override
        public int compare(ResultsEntity o1, ResultsEntity o2) {
            return o2.getTitle().compareToIgnoreCase(o1.getTitle());
        }

};

//başka orderlar burada

}
