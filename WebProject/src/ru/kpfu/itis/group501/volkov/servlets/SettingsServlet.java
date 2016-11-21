package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.entities.Genre;
import ru.kpfu.itis.group501.volkov.entities.User;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by volkov on 18.10.2016.
 */
@MultipartConfig
public class SettingsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        User user = (User) request.getSession().getAttribute("current_user");

        String image = null;
        if (request.getPart("image").getSize()!=0) {
            Part filePart = request.getPart("image");
            image = "profile/"+ Token.getToken()+".jpg";
            AddPhoto.addPhoto(filePart, image);}
        if (image == null){image = user.getImage();}
        String first_name = request.getParameter("first_name");
        if (first_name == null){first_name = user.getFirst_Name();}
        String last_name = request.getParameter("last_name");
        if (last_name == null){last_name = user.getLast_Name();}
        String password = request.getParameter("password");
        String passwordAgain = request.getParameter("passwordAgain");
        String email= request.getParameter("email");
        if (email == null){email = user.getEmail();}
        String country= request.getParameter("country");
        if (country == null){country = user.getCountry();}
        String city= request.getParameter("city");
        if (city == null){image = user.getCity();}
        String about= request.getParameter("about");
        if (about == null){about = user.getAbout();}

        UserService us = new UserService();
        us.update(first_name,last_name,user.getLogin(),password,passwordAgain,email,country,city,about,image,user.getId());
        if (us.getError()==null ){
            List<String> a = new ArrayList();
            GenreService gs = new GenreService();
            a = gs.getGenres();
            for(String g :a){
                if (request.getParameter(g)!=null){
                    gs.addUser(user.getId(),g);
                }
                else{
                    gs.deleteUser(user.getId(),g);
                }
            }
            request.getSession().setAttribute("current_user",us.findId(user.getId()));
            response.sendRedirect("/user?id="+user.getId());
        }
        else{
            response.sendRedirect("/settings&err="+us.getError().getMessage());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("current_user");
        Map<String, Object> root = new HashMap<>();
        List<String> genre = new ArrayList();
        List<String> usergenre = new ArrayList();
        GenreService gs = new GenreService();
        genre = gs.getGenres();
        usergenre = gs.getUserGenres(user.getId());
        List<Genre> a = new ArrayList();
            for (String s :genre){
                Boolean k=false;
                if (!usergenre.isEmpty()) {
                    for (String s2 : usergenre) {
                        if (s.equals(s2) && !k) {
                            a.add(new Genre(s, "true"));
                            k = true;
                        }
                    }
                }
                Genre gg = new Genre(s,null);
                if (!k) a.add(gg);
            }
        root.put("genre",a);
        root.put("err", request.getParameter("err"));
        root.put("user",user);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Helper.render(request,response,"settings.ftl",root);
    }
}
