package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.helpers.Helper;
import ru.kpfu.itis.group501.volkov.services.UserService;
import ru.kpfu.itis.group501.volkov.services.UserServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by volkov on 18.10.2016.
 */
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String passwordAgain = request.getParameter("passwordAgain");
        String email= request.getParameter("email");
        String country= request.getParameter("country");
        String city= request.getParameter("city");
        String about= request.getParameter("about");

        UserServiceInterface us = new UserService();
        us.add(first_name,last_name,login,password,passwordAgain,email,country,city,about);
        if (us.getError()!=null ){
            response.sendRedirect("/login");
        }
        else{
            response.sendRedirect("/registration?err="+us.getError().getMessage());
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("err", request.getParameter("err"));
        Helper.render(request,response,"registration.ftl",root);
    }
}
