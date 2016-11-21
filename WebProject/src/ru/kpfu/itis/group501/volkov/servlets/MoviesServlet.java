package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.entities.Film;
import ru.kpfu.itis.group501.volkov.entities.Genre;
import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.services.FavouriteService;
import ru.kpfu.itis.group501.volkov.services.FilmService;
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
public class MoviesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String genre =request.getParameter("genre");
        String search =request.getParameter("search");
        List<String> a = new ArrayList();
        List<Genre> b = new ArrayList();
        GenreService gs = new GenreService();
        a = gs.getGenres();
        int i =0;

        UserService us = new UserService();
        User user = (User)request.getSession().getAttribute("current_user");
        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        root.put("err", request.getParameter("err"));

        FilmService fs = new FilmService();
        List<Film> films = new ArrayList<>();

        films = fs.getAll();

        if (films != null) {
            if (search !=null && search !=""){
                List<Film> ff = new ArrayList<>();
                for (Film f:films){
                    if (f.getName().toLowerCase().contains(search.toLowerCase())){
                        ff.add(f);
                    }
                }
                films = ff;
            }
            i = 0;
            for (Film c : films) {
                c.setGenre(fs.getGenre(c.getId()));
                String s = fs.getEstimate(c.getId());
                if (s!=null &&s.length()>4){
                    c.setEstimate(s.substring(0,4));
                }
                else c.setEstimate(s);
            }
            if (genre !=null && genre!=""){
                List<Film> films2 = new ArrayList<>();
                for (Film c : films) {

                    if (c.getGenre().contains(genre)){
                        films2.add(c);
                    }
                }
                films = films2;
            }
            for (Film c : films) {
                i++;
                c.setNumber(Integer.toString(i));
            }

        }

        request.getSession().setAttribute("films",films);
        root.put("films", films);



        root.put("gg",a);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Helper.render(request,response,"movies.ftl",root);
    }
}
