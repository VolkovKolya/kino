package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.entities.Like;

import java.util.List;

/**
 * Created by volkov on 25.10.2016.
 */
public interface LikeDaoInterface {
    void addLike(Like like) ;
    void deleteLike(String id) ;
    List<Like> getLikes(String post_id);
}
