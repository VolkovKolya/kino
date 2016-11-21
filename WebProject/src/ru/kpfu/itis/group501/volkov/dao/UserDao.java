package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.helpers.DeleteFromBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntBinaryOperator;

/**
 * Created by volkov on 21.10.2016.
 */

public class UserDao implements UserDaoInterface {

    public void addUser(User user) {
        if (BdSingleton.getConnection() != null && user != null) {
            String request = "INSERT INTO users (first_name,last_name,login,password,email,country,city,about,image) VALUES (?,?,?,?,?,?,?,?,?) ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,user.getFirst_Name());
                st.setString(2,user.getLast_Name());
                st.setString(3,user.getLogin());
                st.setString(4,user.getPassword());
                st.setString(5,user.getEmail());
				st.setString(6,user.getCountry());
                st.setString(7,user.getCity());
                st.setString(8,user.getAbout());
                st.setString(9,user.getImage());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUser(User user) {
        if (BdSingleton.getConnection() != null && user != null) {
            if (user.getPassword() != null) {
                String request = "UPDATE users set first_name = ? ,last_name = ? ,login=? ,password=? ,email=? ,country=? ,city=? ,about=? ,image=? where id = ? ";
                try {
                    PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                    st.setString(1, user.getFirst_Name());
                    st.setString(2, user.getLast_Name());
                    st.setString(3, user.getLogin());
                    st.setString(4, user.getPassword());
                    st.setString(5, user.getEmail());
                    st.setString(6, user.getCountry());
                    st.setString(7, user.getCity());
                    st.setString(8, user.getAbout());
                    st.setString(9, user.getImage());
                    st.setInt(10, Integer.parseInt(user.getId()));
                    st.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                String request = "UPDATE users set first_name = ? ,last_name = ? ,login=?  ,email=? ,country=? ,city=? ,about=? ,image=? where id = ? ";
                try {
                    PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                    st.setString(1, user.getFirst_Name());
                    st.setString(2, user.getLast_Name());
                    st.setString(3, user.getLogin());
                    st.setString(4, user.getEmail());
                    st.setString(5, user.getCountry());
                    st.setString(6, user.getCity());
                    st.setString(7, user.getAbout());
                    st.setString(8, user.getImage());
                    st.setInt(9, Integer.parseInt(user.getId()));
                    st.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public User findUser(String login) {
        if (BdSingleton.getConnection()!= null && !login.equals("")) {
            String request = "SELECT * FROM users WHERE login= ? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,login);
                ResultSet resultSet = st.executeQuery();
                while (resultSet.next()) {
                    return new User(resultSet.getString("id"),resultSet.getString("first_name"),resultSet.getString("last_name"),
                            resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("email"),
                            resultSet.getString("country"),resultSet.getString("city"),resultSet.getString("about"),resultSet.getString("image"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
    public User findUserId(String id) {
        if (BdSingleton.getConnection()!= null && !id.equals("")) {
            String request = "SELECT * FROM users WHERE id= ? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(id));
                ResultSet resultSet = st.executeQuery();
                while (resultSet.next()) {
                    return new User(resultSet.getString("id"),resultSet.getString("first_name"),resultSet.getString("last_name"),
                            resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("email"),
                            resultSet.getString("country"),resultSet.getString("city"),resultSet.getString("about"),resultSet.getString("image"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }


    public void deleteUser(String id) {
        String request = "DELETE FROM users WHERE id = ? ";
        DeleteFromBD d = new DeleteFromBD();
        d.delete(request,id);
    }

    public List getUsers() {
        if (BdSingleton.getConnection()!= null ) {
            String request = "SELECT * FROM users ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                ResultSet resultSet = st.executeQuery();
                List<User> users = new ArrayList();
                while (resultSet.next()) {
                    users.add( new User(resultSet.getString("id"),resultSet.getString("first_name"),resultSet.getString("last_name"),
                            resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("email"),
                            resultSet.getString("country"),resultSet.getString("city"),resultSet.getString("about"),resultSet.getString("image")));
                }
                return users;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

}