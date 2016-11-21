package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.entities.Like;
import ru.kpfu.itis.group501.volkov.helpers.DeleteFromBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by volkov on 25.10.2016.
 */
public class LikeDao implements LikeDaoInterface{

    @Override
    public void addLike(Like like) {
        if (BdSingleton.getConnection() != null && like != null) {
            String request = "INSERT INTO likes (user_id,news_id) VALUES (?,?) ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);

                st.setInt(1,Integer.parseInt(like.getUser_id()));
                st.setInt(2,Integer.parseInt(like.getPost_id()));
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteLike(String user_id, String post_id) {
        String request = "DELETE FROM likes WHERE user_id = ? and news_id= ? ";
        if (BdSingleton.getConnection() != null && !user_id.equals("")&& !post_id.equals("")) {
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st= BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(user_id));
                st.setInt(2,Integer.parseInt(post_id));
                st.executeUpdate();
                st.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
    }

    @Override
    public List<Like> getLikes(String post_id) {
        if (BdSingleton.getConnection()!= null ) {
            String request = "SELECT * FROM likes where news_id=? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(post_id));
                ResultSet resultSet = st.executeQuery();
                List<Like> a = new ArrayList();
                while (resultSet.next()) {
                    a.add(new Like(resultSet.getString("id"),
                            resultSet.getString("user_id"), resultSet.getString("news_id")));
                }
                return a;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
