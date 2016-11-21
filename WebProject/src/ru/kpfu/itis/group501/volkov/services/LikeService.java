package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.dao.LikeDao;
import ru.kpfu.itis.group501.volkov.entities.Error;
import ru.kpfu.itis.group501.volkov.entities.Like;

import java.util.List;

/**
 * Created by volkov on 25.10.2016.
 */
public class LikeService implements LikeServiceInterface {
    private LikeDao likeDao = null;
    private Error error = null;
    public LikeService() {
        this.likeDao = new LikeDao();
    }
    @Override
    public void add(String user_id, String post_id) {
        Like newLike = new Like( user_id,post_id);
        likeDao.addLike(newLike);

    }

    @Override
    public void delete(String user_id, String post_id) {
        likeDao.deleteLike( user_id, post_id);

    }

    @Override
    public List<Like> getLikes(String post_id) {
        error = null;
        if (likeDao.getLikes(post_id) == null) {
            error = new Error("comment_not_found", "Комментариев нет!");
            return null;
        } else {
            return likeDao.getLikes(post_id);
        }
    }

    @Override
    public Error getError() {
        return error;
    }
}
