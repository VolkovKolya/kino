package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.entities.Post;
import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.helpers.DataTime;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.services.PostService;
import ru.kpfu.itis.group501.volkov.services.PostServiceInterface;
import ru.kpfu.itis.group501.volkov.services.UserService;
import ru.kpfu.itis.group501.volkov.services.UserServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by volkov on 18.10.2016.
 */
public class PostCreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String text= request.getParameter("text");
        String video = request.getParameter("video");
        String image = request.getParameter("image");
        User user = (User)request.getSession().getAttribute("current_user");
        String user_id = user.getId();
        String data = new DataTime().getData();


        PostServiceInterface ps = new PostService();
        ps.add(title,text,user_id,data,video,image);
        if (ps.getError()!=null ){
            response.sendRedirect("/mypage");
        }
        else{
            response.sendRedirect("/postcreate?err="+ps.getError().getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("err", request.getParameter("err"));
        Helper.render(request,response,"postcreate.ftl",root);
    }
}
