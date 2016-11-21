package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.entities.Message;
import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.helpers.DataTime;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.services.MessageService;
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
public class MessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id = (String)request.getSession().getAttribute("id");
        User user = (User) request.getSession().getAttribute("current_user");
        if (request.getParameter("delete")!=null){
            MessageService ms = new MessageService();
            ms.delete(request.getParameter("delete"));
            if (ms.getError() == null) {
                response.sendRedirect("/message?id=" + user_id);
            } else {
                response.sendRedirect("/message?id=" + user_id + "&err=" + ms.getError().getMessage());
            }
        }
        if (request.getParameter("text")!=null) {
            String text = request.getParameter("text");

            String from_user_id = user.getId();
            String data = new DataTime().getData();
            MessageService ms = new MessageService();
            ms.add(text, user_id, from_user_id, data);
            if (ms.getError() == null) {
                response.sendRedirect("/message?id=" + user_id);
            } else {
                response.sendRedirect("/message?id=" + user_id + "&err=" + ms.getError().getMessage());
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        request.getSession().setAttribute("id",id);
        MessageService ms = new MessageService();


        UserService us = new UserService();

        User user = (User)request.getSession().getAttribute("current_user");
        User profile = us.findId(id);
        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        root.put("profile", profile);
        root.put("err", request.getParameter("err"));

        List<Message> messages = new ArrayList<>();
        messages = ms.findId(user.getId(),id);
        if (messages != null) {
            for (Message c : messages) {
                User u = us.findId(c.getFrom_user_id());
                c.setUserName(u.getFirst_Name() + " " + u.getLast_Name());
                c.setUserImage(u.getImage());
            }
        }
        root.put("message", messages);


        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Helper.render(request,response,"message.ftl",root);
    }
}
