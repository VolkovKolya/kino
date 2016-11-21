package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.helpers.AddPhoto;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.helpers.Token;
import ru.kpfu.itis.group501.volkov.services.GenreService;
import ru.kpfu.itis.group501.volkov.services.UserService;
import ru.kpfu.itis.group501.volkov.services.UserServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by volkov on 18.10.2016.
 */
@MultipartConfig
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");





        String image = null;
        if (request.getPart("image").getSize()!=0) {
            Part filePart = request.getPart("image");
            image = "profile/"+ Token.getToken()+".jpg";
            AddPhoto.addPhoto(filePart, image);}

        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String login = request.getParameter("login").toLowerCase();
        String password = request.getParameter("password");
        String passwordAgain = request.getParameter("passwordAgain");
        String email= request.getParameter("email");
        String country= request.getParameter("country");
        String city= request.getParameter("city");
        String about= request.getParameter("about");

        UserServiceInterface us = new UserService();
        us.add(first_name,last_name,login,password,passwordAgain,email,country,city,about,image);
        if (us.getError()==null ){
            List<String> a = new ArrayList();
            GenreService gs = new GenreService();
            a = gs.getGenres();
            for(String g :a){
                if (request.getParameter(g)!=null){
                    gs.addUser(us.find(login).getId(),g);
                }
            }
            response.sendRedirect("/posts");
        }
        else{
            response.sendRedirect("/registration?err="+us.getError().getMessage());
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        List<String> a = new ArrayList();
        GenreService gs = new GenreService();
        a = gs.getGenres();
        root.put("genre",a);
        root.put("err", request.getParameter("err"));
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Helper.render(request,response,"registration.ftl",root);
    }
}
