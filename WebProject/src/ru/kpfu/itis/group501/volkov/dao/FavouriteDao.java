package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.entities.Film;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by volkov on 19.11.2016.
 */
public class FavouriteDao implements FavouriteDaoInterface{
    public String getUserFavourite(String user_id,String movie_id){
        String request = "select id from user_film\n" +
                "where user_id=? and movie_id=?";
        if (BdSingleton.getConnection()!= null ) {
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(user_id));
                st.setInt(2,Integer.parseInt(movie_id));
                ResultSet resultSet = st.executeQuery();
                while (resultSet.next()) {
                    return resultSet.getString("id");
                }
                return null;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    public List<Film> getAllFavourite(String user_id){
        String request = "Select * from movie\n" +
                "join user_film\n" +
                "on movie.id = movie_id\n" +
                "where user_id = ?";
        if (BdSingleton.getConnection()!= null ) {
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(user_id));
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

    @Override
    public void add(String user_id, String movie_id) {
        if (BdSingleton.getConnection() != null && movie_id != null && user_id != null) {
            String request = "INSERT INTO user_film (movie_id,user_id) VALUES (?,?) ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1, Integer.parseInt(movie_id));
                st.setInt(2, Integer.parseInt(user_id));
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String user_id, String movie_id) {
        if (BdSingleton.getConnection() != null && movie_id != null && user_id != null) {
            String request = "DELETE FROM user_film WHERE movie_id = ? and user_id= ? ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1, Integer.parseInt(movie_id));
                st.setInt(2, Integer.parseInt(user_id));
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
