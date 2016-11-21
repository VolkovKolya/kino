package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.entities.Film;
import ru.kpfu.itis.group501.volkov.entities.Error;
/**
 * Created by volkov on 27.10.2016.
 */
public interface FilmServiceInterface {
    void add(String name,String description, String video, String image,String year,String country, String producer, String actors);
    void delete(String id);
    Film findId(String id) ;
    Error getError();
}
