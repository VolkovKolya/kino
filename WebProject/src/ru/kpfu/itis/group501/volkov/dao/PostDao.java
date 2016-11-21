package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.entities.Post;
import ru.kpfu.itis.group501.volkov.helpers.DeleteFromBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
                st.setInt(3,Integer.parseInt(post.getUser_id()));
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
                st.setInt(1,Integer.parseInt(id));
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
        String request = "DELETE FROM news WHERE id = ? ";
        DeleteFromBD d = new DeleteFromBD();
        d.delete(request,id);
    }

    public List getLast(){
        String request = "select * from news order by data DESC ";
        return getList(request);
    }
    public List getPopular(){
        String request = "select news.id,news.title,news.text,news.data,news.user_id,news.image,news.video from news " +
                "left join likes on " +
                "news.id = likes.news_id " +
                "group by (news.id) " +
                "order by count(likes.news_id )  DESC ;";
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
                            resultSet.getString("user_id"), resultSet.getString("data"),resultSet.getString("video"),resultSet.getString("image")));
                }
                return a;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
