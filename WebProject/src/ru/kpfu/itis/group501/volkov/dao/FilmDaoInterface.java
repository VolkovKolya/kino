package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.entities.Film;

/**
 * Created by volkov on 19.11.2016.
 */
public interface FilmDaoInterface {
    void addFilm(Film film) ;
    Film findFilmId(String id) ;
    void deleteFilm(String id) ;
}
