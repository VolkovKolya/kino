package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.dao.CommentDao;
import ru.kpfu.itis.group501.volkov.entities.Comment;
import ru.kpfu.itis.group501.volkov.helpers.RegEx;
import ru.kpfu.itis.group501.volkov.entities.Error;
import java.util.List;

/**
 * Created by volkov on 24.10.2016.
 */
public class CommentsService implements CommentsServiceInterface {
    private CommentDao commentDao = null;
    private Error error = null;

    public CommentsService() {
        this.commentDao = new CommentDao();
    }
    @Override
    public void add(String text, String user_id, String post_id, String data) {
        error = null;
        RegEx re = new RegEx();
        if (re.IsRight(text) ){
            if (text != null) {
                Comment newComment = new Comment(text, user_id,post_id, data);
                commentDao.addComment(newComment);
            } else {
                error = new Error("wrong_text", "Вы ввели пустой текст!");
            }
        }else{
            error = new Error("wrong_text", "Не допустимое содержимое !");
        }
    }

    public void addMovie(String text, String user_id, String post_id, String data) {
        error = null;
        RegEx re = new RegEx();
        if (re.IsRight(text) ){
            if (text != null) {
                Comment newComment = new Comment(text, user_id,post_id, data);
                commentDao.addCommentMovie(newComment);
            } else {
                error = new Error("wrong_text", "Вы ввели пустой текст!");
            }
        }else{
            error = new Error("wrong_text", "Не допустимое содержимое !");
        }
    }

    @Override
    public void delete(String id) {
        error = null;
        if (commentDao.findCommentId(id)!=null)
            commentDao.deleteComment(id);
        else {
            error = new Error("comment_not_found", "Искомый комментарий не найден!");
        }
    }

    @Override
    public Comment findId(String id) {
        error = null;
        if (commentDao.findCommentId(id) == null) {
            error = new Error("comment_not_found", "Искомый комментарий не найден!");
            return null;
        } else {
            return commentDao.findCommentId(id);
        }
    }

    @Override
    public Error getError() {
        return error;
    }

    public List<Comment> getComments(String id){
        error = null;
        if (commentDao.getComments(id) == null) {
            error = new Error("comment_not_found", "Комментариев нет!");
            return null;
        } else {
            return commentDao.getComments(id);
        }
    }

    public List<Comment> getCommentsMovie(String id){
        error = null;
        if (commentDao.getCommentsMovie(id) == null) {
            error = new Error("comment_not_found", "Комментариев нет!");
            return null;
        } else {
            return commentDao.getCommentsMovie(id);
        }
    }
}
