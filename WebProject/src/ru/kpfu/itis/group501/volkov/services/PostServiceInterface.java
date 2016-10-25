package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.entities.Error;
import ru.kpfu.itis.group501.volkov.entities.Post;

/**
 * Created by volkov on 23.10.2016.
 */
public interface PostServiceInterface {

    void add(String title,String text, String user_id, String data,String video,String image);
    void delete(String id);
    Post findId(String id) ;
    Error getError();
}
