package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.entities.Post;
import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.helpers.AddPhoto;
import ru.kpfu.itis.group501.volkov.helpers.DataTime;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.helpers.Token;
import ru.kpfu.itis.group501.volkov.services.PostService;
import ru.kpfu.itis.group501.volkov.services.PostServiceInterface;
import ru.kpfu.itis.group501.volkov.services.UserService;
import ru.kpfu.itis.group501.volkov.services.UserServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by volkov on 18.10.2016.
 */
@MultipartConfig
public class PostCreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String image = null;
        if (request.getPart("image").getSize()!=0) {
            Part filePart = request.getPart("image");
            image = "news/"+Token.getToken()+".jpg";
            AddPhoto.addPhoto(filePart, image);}

        String title = request.getParameter("title");
        String text= request.getParameter("text");
        String video = request.getParameter("video");

        User user = (User)request.getSession().getAttribute("current_user");
        String user_id = user.getId();
        String data = new DataTime().getData();


        PostServiceInterface ps = new PostService();
        ps.add(title,text,user_id,data,video,image);
        if (ps.getError()==null ){
            response.sendRedirect("/user?id="+user.getId());
        }
        else{
            response.sendRedirect("/postcreate?err="+ps.getError().getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("err", request.getParameter("err"));
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Helper.render(request,response,"postcreate.ftl",root);
    }
}
