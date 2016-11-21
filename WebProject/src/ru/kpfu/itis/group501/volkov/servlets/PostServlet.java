package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.entities.Comment;
import ru.kpfu.itis.group501.volkov.entities.Like;
import ru.kpfu.itis.group501.volkov.entities.Post;
import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.helpers.DataTime;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.services.*;

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
        String post_id = ((Post) request.getSession().getAttribute("post")).getId();
        User user = (User) request.getSession().getAttribute("current_user");
        if (request.getParameter("like")!=null) {
            if (request.getParameter("like").equals("add")) {
                LikeService lk = new LikeService();
                lk.add(user.getId(), request.getParameter("id"));

            }
            if (request.getParameter("like").equals("delete")) {
                LikeService lk = new LikeService();
                lk.delete(user.getId(), request.getParameter("id"));
            }
            response.sendRedirect("/post?id=" + post_id);
        }
        if (request.getParameter("text")!=null) {
            String text = request.getParameter("text");

            String user_id = user.getId();
            String data = new DataTime().getData();

            CommentsServiceInterface cm = new CommentsService();
            cm.add(text, user_id, post_id, data);
            if (cm.getError() == null) {
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

        UserService us = new UserService();
        post.setNameUser(us.findId(post.getUser_id()).getLogin());
        request.getSession().setAttribute("post",post);

        User user = (User)request.getSession().getAttribute("current_user");

        Map<String, Object> root = new HashMap<>();
        root.put("post", post);
        root.put("user", user);
        root.put("err", request.getParameter("err"));

        List<Comment> comments = new ArrayList<>();
        CommentsService cm = new CommentsService();
        comments = cm.getComments(post.getId());
        for (Comment c:comments){
            c.setUserImage(us.findId(c.getUser_id()).getImage());
            c.setUserName(us.findId(c.getUser_id()).getLogin());
        }
        root.put("comments", comments);

        List<Like> likes = new ArrayList<>();
        LikeService lk = new LikeService();
        likes = lk.getLikes(id);
        String flag = "";
        if (user != null && likes != null) {
            for (Like l : likes) {
                if (l.getUser_id().equals(user.getId()))
                    flag = "true";
            }
        }
        root.put("flag", flag);
        root.put("size", likes.size());
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Helper.render(request,response,"post.ftl",root);
    }
}
