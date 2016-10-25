package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.entities.Post;

/**
 * Created by volkov on 23.10.2016.
 */
public interface PostDaoInterface {
    void addPost(Post post) ;
    Post findPostId(String id) ;
    void deletePost(String id) ;
}
