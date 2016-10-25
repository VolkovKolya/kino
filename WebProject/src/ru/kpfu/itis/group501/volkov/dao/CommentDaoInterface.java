package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.entities.Comment;

/**
 * Created by volkov on 24.10.2016.
 */
public interface CommentDaoInterface {
    void addComment(Comment comment) ;
    Comment findCommentId(String id) ;
    void deleteComment(String id) ;
}
