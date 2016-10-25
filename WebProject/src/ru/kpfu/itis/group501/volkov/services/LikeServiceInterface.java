package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.entities.Like;
import ru.kpfu.itis.group501.volkov.entities.Error;
import java.util.List;

/**
 * Created by volkov on 25.10.2016.
 */
public interface LikeServiceInterface {
    void add( String user_id, String post_id);
    void delete(String id);
    List<Like> getLikes(String post_id);
    Error getError();
}
