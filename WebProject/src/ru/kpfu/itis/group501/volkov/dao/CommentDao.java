package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.entities.Comment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by volkov on 24.10.2016.
 */
public class CommentDao implements CommentDaoInterface{
    @Override
    public void addComment(Comment comment) {
        if (BdSingleton.getConnection() != null && comment != null) {
            String request = "INSERT INTO news_comment (text,user_id,post_id,data) VALUES (?,?,?,?) ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);

                st.setString(1,comment.getText());
                st.setString(2,comment.getUser_id());
                st.setString(3,comment.getPost_id());
                st.setString(4,comment.getData());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Comment findCommentId(String id) {
        if (BdSingleton.getConnection()!= null && !id.equals("")) {
            String request = "SELECT * FROM news_comment WHERE id= ? ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,id);
                ResultSet resultSet = st.executeQuery();
                while (resultSet.next()) {
                    return new Comment(resultSet.getString("id"),resultSet.getString("text"),
                            resultSet.getString("user_id"), resultSet.getString("post_id"), resultSet.getString("data"));
                }
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteComment(String id) {

    }

    public List<Comment> getComments() {
        if (BdSingleton.getConnection()!= null ) {
            String request = "SELECT * FROM news_comment ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                ResultSet resultSet = st.executeQuery();
                List<Comment> a = new ArrayList();
                while (resultSet.next()) {
                    a.add(new Comment(resultSet.getString("id"),resultSet.getString("text"),
                            resultSet.getString("user_id"), resultSet.getString("post_id"),resultSet.getString("data")));
                }
                return a;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }
}
