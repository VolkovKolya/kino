package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.dao.FollowerDao;
import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
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
public class SubscriptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");
        if (request.getParameter("subscription")!=null) {
            FollowerDao fd = new FollowerDao();
            fd.deleteFollower( request.getParameter("subscription"),user.getId());
            response.sendRedirect("/subscription?id=" + user.getId());
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService us = new UserService();


        User user = (User)request.getSession().getAttribute("current_user");

        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        root.put("id",request.getParameter("id"));
        root.put("err", request.getParameter("err"));
        FollowerDao fd = new FollowerDao();
        List<User> users = new ArrayList();
        List<User> follower_from_user = new ArrayList();
        users = fd.getSubscription(request.getParameter("id"));
        root.put("users", users);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Helper.render(request,response,"subscription.ftl",root);
    }
}
