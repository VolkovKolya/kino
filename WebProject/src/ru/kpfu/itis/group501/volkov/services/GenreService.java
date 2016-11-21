package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.dao.GenreDao;

import java.util.List;

/**
 * Created by volkov on 18.11.2016.
 */
public class GenreService implements GenreServiceInterface {
    private GenreDao genreDao = null;
    private Error error = null;

    public GenreService() {
        this.genreDao = new GenreDao();
    }
    @Override
    public void addGenre(String genre) {
        genreDao.addGenre(genre);
    }

    @Override
    public void deleteGenre(String genre) {
        genreDao.deleteGenre(genre);
    }

    @Override
    public void addUser(String user_id, String genre) {
        genreDao.addUser(user_id,genre);
    }

    @Override
    public void deleteUser(String user_id, String genre) {
        genreDao.deleteUser(user_id,genre);
    }

    @Override
    public void addMovie(String movie_id, String genre) {
        genreDao.addMovie(movie_id,genre);
    }

    @Override
    public void deleteMovie(String movie_id, String genre) {
        genreDao.deleteMovie(movie_id,genre);
    }

    @Override
    public List<String> getGenres() {
        return genreDao.getGenres() ;
    }

    public List<String> getUserGenres(String user_id) {
        return genreDao.getUserGenres(user_id) ;
    }
    public List<String> getMovieGenres(String movie_id) {
        return genreDao.getMovieGenres(movie_id) ;
    }

    @Override
    public Error getError() {
        return error;
    }
}
