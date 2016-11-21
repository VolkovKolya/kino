package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.helpers.AddPhoto;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.helpers.Token;
import ru.kpfu.itis.group501.volkov.services.FilmService;
import ru.kpfu.itis.group501.volkov.services.FilmServiceInterface;
import ru.kpfu.itis.group501.volkov.services.GenreService;

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
public class FilmCreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        User user = (User)request.getSession().getAttribute("current_user");
        String image = null;
        if (request.getPart("image").getSize()!=0) {
            Part filePart = request.getPart("image");
            image = "movies/"+ Token.getToken()+".jpg";
            AddPhoto.addPhoto(filePart, image);}

        String name = request.getParameter("name");
        String description= request.getParameter("description");
        String video = request.getParameter("video");
        String year = request.getParameter("year");
        String country= request.getParameter("country");
        String producer = request.getParameter("producer");
        String actors = request.getParameter("actors");


        FilmService f = new FilmService();
        f.add(name,description,video,image,year,country,producer,actors);
        if (f.getError()==null ){
            List<String> a = new ArrayList();
            GenreService gs = new GenreService();
            a = gs.getGenres();
            for(String g :a){
                if (request.getParameter(g)!=null){
                    gs.addMovie(f.findName(name).getId(),g);
                }
            }
            response.sendRedirect("/user?id="+user.getId());
        }
        else{
            response.sendRedirect("/filmcreate?err="+f.getError().getMessage());
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
        Helper.render(request,response,"filmcreate.ftl",root);
    }
}
