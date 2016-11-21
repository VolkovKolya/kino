package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.helpers.DeleteFromBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by volkov on 18.11.2016.
 */
public class GenreDao implements GenreDaoInterface {
    @Override
    public void addGenre(String genre) {
        if (BdSingleton.getConnection() != null && genre != null) {
            String request = "INSERT INTO genre (genre) VALUES (?) ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,genre);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteGenre(String genre) {
        String request = "DELETE FROM genre WHERE genre = ? ";
        DeleteFromBD d = new DeleteFromBD();
        d.deleteString(request,genre);
    }

    @Override
    public void addUser(String user_id, String genre) {
        if (!check(user_id,genre)) {
            if (BdSingleton.getConnection() != null && genre != null && user_id != null) {
                String request = "INSERT INTO favorite_genre (genre,user_id) VALUES (?,?) ";

                try {
                    PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                    st.setString(1, genre);
                    st.setInt(2, Integer.parseInt(user_id));
                    st.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public  boolean check(String user_id, String genre) {
        if (BdSingleton.getConnection()!= null && !user_id.equals("")) {
            String request = "SELECT * FROM favorite_genre WHERE user_id= ? and genre=? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(user_id));
                st.setString(2,genre);
                ResultSet resultSet = st.executeQuery();
                if (resultSet.next()){
                    if (resultSet.getString("user_id") !=null) return true;}
                return false;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return false;
    }
    @Override
    public void deleteUser(String user_id, String genre) {
        if (BdSingleton.getConnection() != null && genre != null && user_id != null) {
            String request = "DELETE FROM favorite_genre WHERE user_id = ? and genre= ? ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(2,genre);
                st.setInt(1,Integer.parseInt(user_id));
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addMovie(String movie_id, String genre) {
        if (BdSingleton.getConnection() != null && genre != null && movie_id != null) {
            String request = "INSERT INTO movie_genre (genre,movie_id) VALUES (?,?) ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,genre);
                st.setInt(2,Integer.parseInt(movie_id));
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteMovie(String movie_id, String genre) {
        if (BdSingleton.getConnection() != null && genre != null && movie_id != null) {
            String request = "DELETE FROM movie_genre WHERE user_id = ? and genre= ? ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,genre);
                st.setInt(2,Integer.parseInt(movie_id));
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getGenres() {
        if (BdSingleton.getConnection()!= null ) {
            String request = "SELECT * FROM genre ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                ResultSet resultSet = st.executeQuery();
                List<String> a = new ArrayList();
                while (resultSet.next()) {
                    a.add(resultSet.getString("genre"));
                }
                return a;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    public List<String> getUserGenres(String user_id) {
        if (BdSingleton.getConnection()!= null ) {
            String request = "SELECT * FROM favorite_genre where user_id=? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(user_id));
                ResultSet resultSet = st.executeQuery();
                List<String> a = new ArrayList();
                while (resultSet.next()) {
                    a.add(resultSet.getString("genre"));
                }
                return a;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    public List<String> getMovieGenres(String movie_id) {
        if (BdSingleton.getConnection()!= null ) {
            String request = "SELECT * FROM movie_genre where movie_id=? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(movie_id));
                ResultSet resultSet = st.executeQuery();
                List<String> a = new ArrayList();
                while (resultSet.next()) {
                    a.add(resultSet.getString("genre"));
                }
                return a;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
