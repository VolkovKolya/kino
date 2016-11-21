package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.entities.Film;
import ru.kpfu.itis.group501.volkov.helpers.DeleteFromBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by volkov on 19.11.2016.
 */
public class FilmDao implements FilmDaoInterface {

    @Override
    public void addFilm(Film film) {
        if (BdSingleton.getConnection() != null && film != null) {
            String request = "INSERT INTO movie (name,country,year,producer,trailer,image,actors,description) VALUES (?,?,?,?,?,?,?,?) ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,film.getName());
                st.setString(2,film.getCountry());
                st.setInt(3,Integer.parseInt(film.getYear()));
                st.setString(4,film.getProducer());
                st.setString(5,film.getVideo());
                st.setString(6,film.getImage());
                st.setString(7,film.getActors());
                st.setString(8,film.getDescription());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Film findFilmId(String id) {
        if (BdSingleton.getConnection()!= null && !id.equals("")) {
            String request = "SELECT * FROM movie WHERE id= ? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(id));
                ResultSet resultSet = st.executeQuery();
                while (resultSet.next()) {
                    return new Film(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("description"),
                            resultSet.getString("trailer"), resultSet.getString("image"), resultSet.getString("year"),resultSet.getString("country"),resultSet.getString("producer"),resultSet.getString("actors"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
    public Film findFilmName(String name) {
        if (BdSingleton.getConnection()!= null && !name.equals("")) {
            String request = "SELECT * FROM movie WHERE name= ? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,name);
                ResultSet resultSet = st.executeQuery();
                while (resultSet.next()) {
                    return new Film(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("description"),
                            resultSet.getString("trailer"), resultSet.getString("image"), resultSet.getString("year"),resultSet.getString("country"),resultSet.getString("producer"),resultSet.getString("actors"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteFilm(String id) {
        String request = "DELETE FROM movie WHERE id = ? ";
        DeleteFromBD d = new DeleteFromBD();
        d.delete(request,id);
    }

    public List getAll(){
        String request = "select movie.* ,avg(estimate.estimate) from estimate\n" +
                "join movie on\n" +
                "movie_id=movie.id\n" +
                "group by (movie_id,movie.id)\n" +
                "order by avg(estimate.estimate) Desc";
        if (BdSingleton.getConnection()!= null ) {

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                ResultSet resultSet = st.executeQuery();
                List<Film> a = new ArrayList();
                while (resultSet.next()) {
                    a.add(new Film(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("description"),
                            resultSet.getString("trailer"), resultSet.getString("image"), resultSet.getString("year"),resultSet.getString("country"),resultSet.getString("producer"),resultSet.getString("actors")));
                }
                return a;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    public String getEstimate(String movie_id) {
        String request = "select avg(estimate) as es from estimate\n" +
                "where movie_id=?";
        if (BdSingleton.getConnection()!= null ) {

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(movie_id));
                ResultSet resultSet = st.executeQuery();

                while (resultSet.next()) {
                   return resultSet.getString("es");
                }

            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    public String getGenre(String movie_id) {
        String request = "select genre as g from movie_genre\n" +
                "where movie_id=?\n";
        if (BdSingleton.getConnection()!= null ) {
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(movie_id));
                ResultSet resultSet = st.executeQuery();
                String s="";
                while (resultSet.next()) {
                    s=s+ resultSet.getString("g")+", ";
                }
                return s;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

}
