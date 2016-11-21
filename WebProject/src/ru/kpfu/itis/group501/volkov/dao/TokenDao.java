package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.helpers.DeleteFromBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by volkov on 21.10.2016.
 */
public class TokenDao implements  TokenDaoInterface {
    public void addToken(String id, String token) {
        if (check(id)){
            updateToken(id, token);
        }

        else{
            if (BdSingleton.getConnection() != null && !id.equals("") && !token.equals("")) {
                String request = "INSERT INTO cookies (id,token) VALUES ( ? , ? )";
                try {
                    PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                    st.setInt(1,Integer.parseInt(id));
                    st.setString(2,token);
                    st.executeUpdate();
                    st.close();
                } catch (SQLException sql) {
                    sql.printStackTrace();
                }
            }
        }

    }
    public boolean check(String id){
        if (BdSingleton.getConnection() != null && !id.equals("")) {
            String request = "SELECT * FROM cookies WHERE id= ? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(id));
                ResultSet resultSet = st.executeQuery();
                if (resultSet.next()){
                if (resultSet.getString("id") !=null) return true;}
                return false;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return false;
    }
    public void updateToken(String id, String token) {
        if (BdSingleton.getConnection() != null && !id.equals("") && !token.equals("")) {
            String request = "UPDATE cookies SET token = ?  WHERE id = ? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,token);
                st.setInt(2,Integer.parseInt(id));
                st.executeUpdate();
                st.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
    }

    public void deleteToken(String token) {
        String request = "DELETE FROM cookies WHERE token = ? ";
        DeleteFromBD d = new DeleteFromBD();
        d.deleteString(request,token);
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