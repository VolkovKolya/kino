package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.entities.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by volkov on 23.10.2016.
 */
public class PostDao implements PostDaoInterface {
    @Override
    public void addPost(Post post) {
        if (BdSingleton.getConnection() != null && post != null) {
            String request = "INSERT INTO news (title,text,user_id,data,video,image) VALUES (?,?,?,?,?,?) ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,post.getTitle());
                st.setString(2,post.getText());
                st.setString(3,post.getUser_id());
                st.setString(4,post.getData());
                st.setString(5,post.getVideo());
                st.setString(6,post.getImage());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Post findPostId(String id) {
        if (BdSingleton.getConnection()!= null && !id.equals("")) {
            String request = "SELECT * FROM news WHERE id= ? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,id);
                ResultSet resultSet = st.executeQuery();
                while (resultSet.next()) {
                    return new Post(resultSet.getString("id"),resultSet.getString("title"),resultSet.getString("text"),
                            resultSet.getString("user_id"), resultSet.getString("data"), resultSet.getString("video"),resultSet.getString("image"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deletePost(String id) {

    }

    public List getLast(){
        String request = "in progress";
        return getList(request);
    }
    public List getPopular(){
        String request = "in progress";
        return getList(request);
    }
    public List getList(String request){
        if (BdSingleton.getConnection()!= null ) {

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                ResultSet resultSet = st.executeQuery();
                List<Post> a = new ArrayList();
                while (resultSet.next()) {
                    a.add(new Post(resultSet.getString("id"),resultSet.getString("title"),resultSet.getString("text"),
                            resultSet.getString("user_id"), resultSet.getString("data"),resultSet.getString("image")));
                }
                return a;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
