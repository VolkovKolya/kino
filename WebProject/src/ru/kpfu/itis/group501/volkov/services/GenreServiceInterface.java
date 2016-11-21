package ru.kpfu.itis.group501.volkov.services;


import java.util.List;

/**
 * Created by volkov on 18.11.2016.
 */
public interface GenreServiceInterface {
    void addGenre( String genre);
    void deleteGenre(String genre);

    void addUser( String user_id,String genre_id);
    void deleteUser(String user_id,String genre_id);

    void addMovie( String movie_id,String genre_id);
    void deleteMovie(String movie_id,String genre_id);

    List<String> getGenres();
    Error getError();
}
