package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.dao.FilmDao;
import ru.kpfu.itis.group501.volkov.entities.Film;
import ru.kpfu.itis.group501.volkov.entities.Error;
import ru.kpfu.itis.group501.volkov.entities.Post;
import ru.kpfu.itis.group501.volkov.helpers.RegEx;

import java.util.List;

/**
 * Created by volkov on 27.10.2016.
 */
public class FilmService implements FilmServiceInterface {
    private FilmDao filmDao = null;
    private Error error = null;

    public FilmService() {
        this.filmDao = new FilmDao();
    }
    @Override
    public void add(String name, String description, String video, String image, String year, String country, String producer, String actors) {
        error = null;
        RegEx re = new RegEx();
        if (re.IsRight(name) ){
            if (description != null) {
                Film newFilm = new Film(name, description, video,  image, year, country, producer, actors);
                filmDao.addFilm(newFilm);
            } else {
                error = new Error("wrong_text", "Вы ввели пустой текст!");
            }
        }else{
            error = new Error("wrong_title", "Не правильное название фильма");
        }
    }

    @Override
    public void delete(String id) {
        error = null;
        if (filmDao.findFilmId(id)!=null)
            filmDao.deleteFilm(id);
        else {
            error = new Error("film_not_found", "Искомый фильм не найден!");
        }
    }

    @Override
    public Film findId(String id) {
        error = null;
        if (filmDao.findFilmId(id) == null) {
            error = new Error("film_not_found", "Искомый фильм не найден!");
            return null;
        } else {
            return filmDao.findFilmId(id);
        }
    }
    public Film findName(String name) {
        error = null;
        if (filmDao.findFilmName(name) == null) {
            error = new Error("film_not_found", "Искомый фильм не найден!");
            return null;
        } else {
            return filmDao.findFilmName(name);
        }
    }

    public List<Film> getAll(){
        return filmDao.getAll();
    }

    @Override
    public Error getError() {
        return error;
    }
    public String getEstimate(String movie_id){return filmDao.getEstimate(movie_id);}
    public String getGenre(String movie_id){return filmDao.getGenre(movie_id);}

}
