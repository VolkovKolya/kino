package ru.kpfu.itis.group501.volkov.dao;

/**
 * Created by volkov on 19.11.2016.
 */
public interface FavouriteDaoInterface {
    void add(String user_id,String movie_id) ;
    void delete(String user_id,String movie_id) ;

}
