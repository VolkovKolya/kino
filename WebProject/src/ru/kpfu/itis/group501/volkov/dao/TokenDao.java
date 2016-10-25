package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by volkov on 21.10.2016.
 */
public class TokenDao implements  TokenDaoInterface {
    public void addToken(String id, String token) {
        if (BdSingleton.getConnection() != null && !id.equals("") && !token.equals("")) {
            String request = "INSERT INTO cookies (id,token) VALUES ( ? , ? )";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,id);
                st.setString(2,token);
                st.executeUpdate();
                st.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
    }

    public void updateToken(String id, String token) {
        if (BdSingleton.getConnection() != null && !id.equals("") && !token.equals("")) {
            String request = "UPDATE cookies SET token = ?  WHERE id = ? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,token);
                st.setString(2,id);
                st.executeUpdate();
                st.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
    }

    public void deleteToken(String token) {
        if (BdSingleton.getConnection() != null && !token.equals("")) {
            String request = "DELETE FROM cookies WHERE token = ? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st= BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,token);
                st.executeUpdate();
                st.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
    }

    public String findToken(String token) {
        if (BdSingleton.getConnection() != null && !token.equals("")) {
            String request = "SELECT * FROM cookies " +
                    "WHERE token= ? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,token);
                ResultSet resultSet = st.executeQuery();
                while (resultSet.next()) {
                    return resultSet.getString("id");
                }
                st.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

}