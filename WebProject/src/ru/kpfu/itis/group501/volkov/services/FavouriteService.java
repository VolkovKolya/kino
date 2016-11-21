package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.dao.FavouriteDao;
import ru.kpfu.itis.group501.volkov.dao.FilmDao;
import ru.kpfu.itis.group501.volkov.entities.Error;
import ru.kpfu.itis.group501.volkov.entities.Film;

import java.util.List;

/**
 * Created by volkov on 19.11.2016.
 */
public class FavouriteService {

    private FavouriteDao favouriteDao = null;
    private Error error = null;

    public FavouriteService() {
        this.favouriteDao = new FavouriteDao();
    }

    public void add(String user_id,String movie_id) {
        favouriteDao.add(user_id,movie_id);

    }

    public void delete(String user_id,String movie_id) {
            favouriteDao.delete(user_id,movie_id);

    }
    public String getUserFavourite(String user_id,String movie_id){return favouriteDao.getUserFavourite(user_id,movie_id);}
    public List<Film> getAllFavourite(String user_id){return favouriteDao.getAllFavourite(user_id);}
    public Error getError(){return error;}
}
