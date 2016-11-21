package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.entities.Message;

import java.util.List;

/**
 * Created by volkov on 18.11.2016.
 */
public interface MessageServiceInterface {
    void add(String text, String user_id, String from_user_id,String data);
    void delete(String id);
    List<Message> findId(String user_id, String from_user_id) ;
    Error getError();
}
