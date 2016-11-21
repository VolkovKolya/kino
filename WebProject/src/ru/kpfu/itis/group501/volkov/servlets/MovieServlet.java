package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.dao.EstimateDao;
import ru.kpfu.itis.group501.volkov.entities.*;
import ru.kpfu.itis.group501.volkov.helpers.DataTime;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.services.*;

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
 * Created by volkov on 19.11.2016.
 */
public class MovieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movie_id = ((Film) request.getSession().getAttribute("film")).getId();
        User user = (User) request.getSession().getAttribute("current_user");
        if (request.getParameter("add")!=null) {
            FavouriteService fs = new FavouriteService();
            fs.add( user.getId(),movie_id);
            response.sendRedirect("/movie?id=" + movie_id);
            return;
        }
        if (request.getParameter("delete")!=null) {
            FavouriteService fs = new FavouriteService();
            fs.delete( user.getId(),movie_id);
            response.sendRedirect("/movie?id=" + movie_id);
            return;
        }

        if (request.getParameter("estimate")!=null) {
            EstimateDao ud = new EstimateDao();
            ud.add( new Estimate(user.getId(),movie_id,request.getParameter("estimate")));
            response.sendRedirect("/movie?id=" + movie_id);
            return;
        }
        if (request.getParameter("text")!=null) {
            String text = request.getParameter("text");

            String user_id = user.getId();
            String data = new DataTime().getData();

            CommentsService cm = new CommentsService();
            cm.addMovie(text, user_id, movie_id, data);
            if (cm.getError() == null) {
                response.sendRedirect("/movie?id=" + movie_id);
            } else {
                response.sendRedirect("/movie?id=" + movie_id + "&err=" + cm.getError().getMessage());
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        FilmService ps = new FilmService();
        Film film = ps.findId(id);

        UserService us = new UserService();
        User user = (User)request.getSession().getAttribute("current_user");

        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        root.put("err", request.getParameter("err"));

        String s = ps.getEstimate(id);
        if (s!=null &&s.length()>4){
            film.setEstimate(s.substring(0,4));
        }
        else{
            film.setEstimate(s);
        }
        film.setGenre(ps.getGenre(id));
        request.getSession().setAttribute("film",film);
        root.put("film", film);

        if (user !=null) {
            FavouriteService fs = new FavouriteService();
            root.put("fav", fs.getUserFavourite(user.getId(), id));

            EstimateDao ed = new EstimateDao();
            Estimate e = ed.find(user.getId(), id);
            root.put("est", e);
        }
        else{

        }


        List<Comment> comments = new ArrayList<>();
        CommentsService cm = new CommentsService();
        comments = cm.getCommentsMovie(film.getId());
        for (Comment c:comments){
            c.setUserImage(us.findId(c.getUser_id()).getImage());
            c.setUserName(us.findId(c.getUser_id()).getLogin());
        }
        root.put("comments", comments);


        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Helper.render(request,response,"movie.ftl",root);
    }
}
