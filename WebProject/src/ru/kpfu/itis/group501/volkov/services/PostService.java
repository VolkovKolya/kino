package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.dao.PostDao;
import ru.kpfu.itis.group501.volkov.entities.Error;
import ru.kpfu.itis.group501.volkov.entities.Post;
import ru.kpfu.itis.group501.volkov.helpers.RegEx;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by volkov on 23.10.2016.
 */
public class PostService implements PostServiceInterface {
    private PostDao postDao = null;
    private Error error = null;

    public PostService() {
        this.postDao = new PostDao();
    }
    @Override
    public void add(String title,String text, String user_id, String data,String video,String image) {
        error = null;
        RegEx re = new RegEx();
        if (re.IsRight(title) ){
            if (text != null) {
                Post newPost = new Post(title,text, user_id, data,video,image);
                postDao.addPost(newPost);
            } else {
                error = new Error("wrong_text", "Вы ввели пустой текст!");
            }
        }else{
            error = new Error("wrong_title", "Не правильное название новости");
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Post findId(String id) {
        error = null;
        if (postDao.findPostId(id) == null) {
            error = new Error("post_not_found", "Искомый пост не найден!");
            return null;
        } else {
            return postDao.findPostId(id);
        }
    }

    public List getNews(String sort){
        if (sort!=null){
            if (sort.equals("Last")) return postDao.getLast();
            else return postDao.getPopular();
        }
        else{
            return postDao.getLast();
        }
    }

    @Override
    public Error getError() {
        return error;
    }
}
