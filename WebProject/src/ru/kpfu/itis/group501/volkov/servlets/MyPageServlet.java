package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.dao.FollowerDao;
import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.services.GenreService;
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
public class MyPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");
        if (request.getParameter("follower")!=null) {
            FollowerDao fd = new FollowerDao();
            fd.addFollower( request.getParameter("follower"),user.getId());
            response.sendRedirect("/user?id=" + request.getParameter("follower"));
            return;
        }
        if (request.getParameter("subscription")!=null) {
            FollowerDao fd = new FollowerDao();
            fd.deleteFollower( request.getParameter("subscription"),user.getId());
            response.sendRedirect("/user?id=" + request.getParameter("subscription"));
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        UserService us = new UserService();

        User user = (User)request.getSession().getAttribute("current_user");
        User profile =us.findId(id);
        if (profile == null) response.sendRedirect("/posts?err=User not found");


        FollowerDao fd = new FollowerDao();
        List<User> follower_from_user = new ArrayList();
        follower_from_user = fd.getSubscription(user.getId());
        String flag = null;
            for(User u:follower_from_user){
                if (u.getId().equals(id) ){
                    flag="true";
                }
            }

        List<String> genre = new ArrayList();
        GenreService gs = new GenreService();
        genre = gs.getUserGenres(id);
        Map<String, Object> root = new HashMap<>();
        root.put("flag",flag);
        root.put("genre",genre);
        root.put("user", user);
        root.put("profile", profile);
        root.put("err", request.getParameter("err"));


        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Helper.render(request,response,"mypage.ftl",root);
    }
}
