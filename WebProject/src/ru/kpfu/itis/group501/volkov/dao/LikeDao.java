package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.entities.Like;

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
            String request = "INSERT INTO like (user_id,post_id) VALUES (?,?) ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);

                st.setString(1,like.getUser_id());
                st.setString(2,like.getPost_id());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteLike(String id) {

    }

    @Override
    public List<Like> getLikes(String post_id) {
        if (BdSingleton.getConnection()!= null ) {
            String request = "SELECT * FROM like where post_id=? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,post_id);
                ResultSet resultSet = st.executeQuery();
                List<Like> a = new ArrayList();
                while (resultSet.next()) {
                    a.add(new Like(resultSet.getString("id"),
                            resultSet.getString("user_id"), resultSet.getString("post_id")));
                }
                return a;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
