package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.entities.Post;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.services.PostService;
import ru.kpfu.itis.group501.volkov.services.PostServiceInterface;

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


        String sort = request.getParameter("sort");


        Map<String, Object> root = new HashMap<>();
        List<Post> posts = new ArrayList<>();
        PostService ps = new PostService();
        posts = ps.getNews(sort);

        root.put("posts", posts);
        root.put("login", request.getSession().getAttribute("current_user"));

        Helper.render(request,response,"post.ftl",root);
    }
}
