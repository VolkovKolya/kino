package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.entities.Post;
import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.services.PostService;
import ru.kpfu.itis.group501.volkov.services.PostServiceInterface;
import ru.kpfu.itis.group501.volkov.services.UserService;

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
 * Created by volkov on 18.10.2016.
 */
public class PostsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService us = new UserService();

        String sort = request.getParameter("sort");


        Map<String, Object> root = new HashMap<>();
        List<Post> posts = new ArrayList<>();
        PostService ps = new PostService();
        posts = ps.getNews(sort);
        for(Post p:posts){
            p.setNameUser(us.findId(p.getUser_id()).getLogin());
            p.setText(p.getText().substring(0,610)+"...");
        }

        User user = (User)request.getSession().getAttribute("current_user");
        root.put("user", user);
        root.put("posts", posts);
        root.put("err", request.getParameter("err"));
        root.put("login", request.getSession().getAttribute("current_user"));
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Helper.render(request,response,"posts.ftl",root);
    }
}
