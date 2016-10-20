package ru.kpfu.itis.group501.volkov.servlets;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.helpers.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by volkov on 18.10.2016.
 */
public class LoginServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login").toLowerCase();
        String password = request.getParameter("password");

        try {
            PreparedStatement st = BdSingleton.getConnection().prepareStatement("select users.login,password from users where users.login = ?");
            st.setString(1,login);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (password.equals(rs.getString("password"))) {
                    request.getSession().setAttribute("current_user", login);
                    Cookie cookie = new Cookie("current_user",login);
                    cookie.setMaxAge(24*60*60);
                    response.addCookie(cookie);
                    response.sendRedirect("/secret");
                } else {
                    response.sendRedirect("/login?err=Incorrect Password&login=" + login);
                }
            }
            else {
                response.sendRedirect("/login?err=Wrong login&login=" + login);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
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
