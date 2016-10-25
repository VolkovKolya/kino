package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.configs.BdSingleton;

import java.sql.*;
import java.util.List;
/**
 * Created by volkov on 21.10.2016.
 */

public class UserDao implements UserDaoInterface {

    public void addUser(User user) {
        if (BdSingleton.getConnection() != null && user != null) {
            String request = "INSERT INTO users (first_name,last_name,login,password,email,country,city,about) VALUES (?,?,?,?,?,?,?,?) ";

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
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public User findUser(String login) {
        if (BdSingleton.getConnection()!= null && !login.equals("")) {
            String reguest = "SELECT * FROM users WHERE login= ? ";
            return selectRequest(reguest, login);
        }
        return null;
    }
    public User findUserId(String id) {
        if (BdSingleton.getConnection()!= null && !id.equals("")) {
            String request = "SELECT * FROM users WHERE id= ? ";
            return selectRequest(request,id);
        }
        return null;
    }

    public User selectRequest(String request, String param) {
        try {
            PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
            st.setString(1,param);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                return new User(resultSet.getString("id"),resultSet.getString("first_name"),resultSet.getString("last_name"),
                        resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("email"),
                        resultSet.getString("country"),resultSet.getString("city"),resultSet.getString("about"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return null;
    }


    public void deleteUser(String id) {

    }


}