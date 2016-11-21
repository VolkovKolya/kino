package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.entities.Message;

import java.util.List;

/**
 * Created by volkov on 18.11.2016.
 */
public interface MessageDaoInterface {
    void addMessage(Message message) ;
    List<Message> findMessageId(String user_id, String from_user_id) ;
    void deleteMessage(String id) ;
}
