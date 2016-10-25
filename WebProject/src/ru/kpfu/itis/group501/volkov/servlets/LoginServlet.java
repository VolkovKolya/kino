package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.helpers.Hash;
import ru.kpfu.itis.group501.volkov.helpers.Token;
import ru.kpfu.itis.group501.volkov.services.TokenService;
import ru.kpfu.itis.group501.volkov.services.UserService;
import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.helpers.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by volkov on 18.10.2016.
 */
public class LoginServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login").toLowerCase();
        String password = req.getParameter("password");

        UserService userService = new UserService();
        User currentUser = userService.find(login);
        if (currentUser != null) {
            if (Hash.getMd5Apache(password).equals(currentUser.getPassword())) {

                req.getSession().setAttribute("current_user", currentUser);

                String token = Token.getToken();
                Cookie cookie = new Cookie("current_user",token);
                cookie.setMaxAge(30*24*60*60);
                resp.addCookie(cookie);
                TokenService ts = new TokenService();
                ts.addToken(""+currentUser.getId(), token);

                resp.sendRedirect("/mypage");
            } else {
                resp.sendRedirect("/login?err=Неправильный пароль&login=" + login);
            }
        } else {
            resp.sendRedirect("/login?err="+userService.getError().getMessage());
        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("err", request.getParameter("err"));
        root.put("login", request.getParameter("login"));
        response.setContentType("text/html; charset=utf-8");
        Helper.render(request,response,"login.ftl",root);

    }
}
