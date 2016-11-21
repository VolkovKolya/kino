package ru.kpfu.itis.group501.volkov.dao;

/**
 * Created by volkov on 18.11.2016.
 */
public interface GenreDaoInterface {
    void addGenre(String genre) ;
    void deleteGenre(String genre) ;

    void addUser(String user_id, String genre_id) ;
    void deleteUser(String user_id, String genre_id) ;

    void addMovie(String movie_id, String genre_id) ;
    void deleteMovie(String movie_id, String genre_id) ;




}
