package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.entities.Comment;
import ru.kpfu.itis.group501.volkov.entities.Error;

/**
 * Created by volkov on 24.10.2016.
 */
public interface CommentsServiceInterface {

    void add(String text, String user_id, String post_id,String data);
    void delete(String id);
    Comment findId(String id) ;
    Error getError();
}
