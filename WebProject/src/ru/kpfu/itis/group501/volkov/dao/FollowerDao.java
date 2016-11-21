package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by volkov on 18.11.2016.
 */
public class FollowerDao implements FollowerDaoInterface {
    @Override
    public void addFollower(String user_id, String profile_id) {
        if (BdSingleton.getConnection() != null && user_id!= null && profile_id!= null) {
            String request = "INSERT INTO subscription (user_id,on_user_id) VALUES (?,?) ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);

                st.setInt(1,Integer.parseInt(user_id));
                st.setInt(2,Integer.parseInt(profile_id));
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteFollower(String user_id, String profile_id) {
        String request = "DELETE FROM subscription  WHERE user_id = ? and on_user_id= ? ";
        if (BdSingleton.getConnection() != null && user_id !=null && profile_id !=null) {
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);

                st.setInt(1,Integer.parseInt(user_id));
                st.setInt(2,Integer.parseInt(profile_id));
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getFollowers(String user_id) {
        if (BdSingleton.getConnection()!= null ) {
            String request = "Select users.id,login,password,first_name,last_name,email,country,city,about,image from users join subscription on users.id = on_user_id where user_id = ?";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(user_id));
                ResultSet resultSet = st.executeQuery();
                List<User> a = new ArrayList();
                while (resultSet.next()) {
                    a.add(new User(resultSet.getString("id"),resultSet.getString("first_name"),resultSet.getString("last_name"),
                            resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("email"),
                            resultSet.getString("country"),resultSet.getString("city"),resultSet.getString("about"),resultSet.getString("image")));
                }
                return a;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    public List<User> getSubscription(String user_id) {
        if (BdSingleton.getConnection()!= null ) {
            String request = "Select users.id,login,password,first_name,last_name,email,country,city,about,image from users join subscription on users.id = user_id where on_user_id = ?";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(user_id));
                ResultSet resultSet = st.executeQuery();
                List<User> a = new ArrayList();
                while (resultSet.next()) {
                    a.add(new User(resultSet.getString("id"),resultSet.getString("first_name"),resultSet.getString("last_name"),
                            resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("email"),
                            resultSet.getString("country"),resultSet.getString("city"),resultSet.getString("about"),resultSet.getString("image")));
                }
                return a;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
