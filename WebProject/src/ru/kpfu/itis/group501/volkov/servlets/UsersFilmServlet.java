package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.entities.Film;
import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.services.FavouriteService;
import ru.kpfu.itis.group501.volkov.services.FilmService;
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
import java.util.function.IntBinaryOperator;

/**
 * Created by volkov on 18.10.2016.
 */
public class UsersFilmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");
        if (request.getParameter("delete")!=null){
            FavouriteService fs = new FavouriteService();
            fs.delete(user.getId(),request.getParameter("delete"));
            if (fs.getError() == null) {
                response.sendRedirect("/favourite?id=" + user.getId());
            } else {
                response.sendRedirect("/favourite?id=" + user.getId() + "&err=" + fs.getError().getMessage());
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        UserService us = new UserService();
        User profile = us.findId(id);
        User user = (User)request.getSession().getAttribute("current_user");
        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        root.put("profile", profile);
        root.put("err", request.getParameter("err"));

        FilmService ffss = new FilmService();
        FavouriteService fs = new FavouriteService();
        List<Film> favourite = new ArrayList<>();
        favourite = fs.getAllFavourite(id);
        if (favourite != null) {
            int i = 0;
            for (Film c : favourite) {
                c.setGenre(ffss.getGenre(c.getId()));
                i++;
                c.setNumber(Integer.toString(i));

            }
        }

        root.put("favourite", favourite);


        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Helper.render(request,response,"favourite.ftl",root);
    }
}
