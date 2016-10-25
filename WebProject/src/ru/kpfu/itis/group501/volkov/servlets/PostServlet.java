package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.entities.Comment;
import ru.kpfu.itis.group501.volkov.entities.Like;
import ru.kpfu.itis.group501.volkov.entities.Post;
import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.helpers.DataTime;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.services.CommentsService;
import ru.kpfu.itis.group501.volkov.services.CommentsServiceInterface;
import ru.kpfu.itis.group501.volkov.services.LikeService;
import ru.kpfu.itis.group501.volkov.services.PostService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by volkov on 24.10.2016.
 */
public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("current_user");
        if (request.getParameter("like")!=null){
            LikeService lk = new LikeService();
            lk.add(user.getId(),request.getParameter("id"));
        }
        if (request.getParameter("text")!=null) {
            String text = request.getParameter("text");

            String user_id = user.getId();
            String data = new DataTime().getData();
            String post_id = ((Post) request.getSession().getAttribute("post")).getId();
            CommentsServiceInterface cm = new CommentsService();
            cm.add(text, user_id, post_id, data);
            if (cm.getError() != null) {
                response.sendRedirect("/post?id=" + post_id);
            } else {
                response.sendRedirect("/post?id=" + post_id + "&err=" + cm.getError().getMessage());
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        PostService ps = new PostService();
        Post post = ps.findId(id);
        request.getSession().setAttribute("post",post);

        Map<String, Object> root = new HashMap<>();
        root.put("post", post);
        root.put("login", request.getSession().getAttribute("current_user"));
        root.put("err", request.getParameter("err"));

        List<Comment> comments = new ArrayList<>();
        CommentsService cm = new CommentsService();
        comments = cm.getComments();
        root.put("comments", comments);

        List<Like> likes = new ArrayList<>();
        LikeService lk = new LikeService();
        likes = lk.getLikes(id);
        root.put("likes", likes);

        Helper.render(request,response,"post.ftl",root);
    }
}
