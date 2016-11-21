package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.entities.Message;
import ru.kpfu.itis.group501.volkov.helpers.DeleteFromBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by volkov on 18.11.2016.
 */
public class MessageDao implements MessageDaoInterface{
    @Override
    public void addMessage(Message message) {
        if (BdSingleton.getConnection() != null && message != null) {
            String request = "INSERT INTO message (text,to_user_id,from_user_id,data) VALUES (?,?,?,?) ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);

                st.setString(1,message.getText());
                st.setInt(2,Integer.parseInt(message.getUser_id()));
                st.setInt(3,Integer.parseInt(message.getFrom_user_id()));
                st.setString(4,message.getData());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Message> findMessageId(String user_id, String from_user_id) {
        if (BdSingleton.getConnection()!= null && !user_id.equals("") && !from_user_id.equals("")) {
            String request = "SELECT * FROM message WHERE (to_user_id= ? and from_user_id=?) or (to_user_id= ? and from_user_id=?) ";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(user_id));
                st.setInt(2,Integer.parseInt(from_user_id));
                st.setInt(4,Integer.parseInt(user_id));
                st.setInt(3,Integer.parseInt(from_user_id));
                ResultSet resultSet = st.executeQuery();
                List<Message> a = new ArrayList();
                while (resultSet.next()) {
                    a.add( new Message(resultSet.getString("id"),resultSet.getString("text"),
                            resultSet.getString("to_user_id"), resultSet.getString("from_user_id"), resultSet.getString("data")));
                }
                return a;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    public List<Message> findOneId(String user_id) {
        if (BdSingleton.getConnection()!= null && !user_id.equals("") ) {
            String request = "SELECT message.id,message.to_user_id,message.from_user_id,message.text,message.data FROM message \n" +
                    "join users on\n" +
                    "(users.id = to_user_id or users.id = from_user_id)\n" +
                    "WHERE from_user_id= ? or to_user_id = ? \n" +
                    "Group by( message.id,users.id)\n" +
                    "Having message.id=(select max(id) FROM message \n" +
                    "WHERE (from_user_id= ? and to_user_id = users.id)  or (to_user_id = ? and from_user_id = users.id )) Order by id desc";
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1,Integer.parseInt(user_id));
                st.setInt(2,Integer.parseInt(user_id));
                st.setInt(3,Integer.parseInt(user_id));
                st.setInt(4,Integer.parseInt(user_id));
                ResultSet resultSet = st.executeQuery();
                List<Message> a = new ArrayList();
                while (resultSet.next()) {
                    a.add( new Message(resultSet.getString("id"),resultSet.getString("text"),
                            resultSet.getString("to_user_id"), resultSet.getString("from_user_id"), resultSet.getString("data")));
                }
                return a;
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteMessage(String id) {
        String request = "DELETE FROM message WHERE id = ? ";
        DeleteFromBD d = new DeleteFromBD();
        d.delete(request,id);
    }
}
