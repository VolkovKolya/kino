package ru.kpfu.itis.group501.volkov.servlets;

/**
 * Created by volkov on 23.10.2016.
 */

        import ru.kpfu.itis.group501.volkov.services.TokenService;
        import ru.kpfu.itis.group501.volkov.services.TokenServiceInterface;

        import javax.servlet.ServletException;
        import javax.servlet.http.Cookie;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;


public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Cookie[] cookies = req.getCookies();

        for (Cookie cookie: cookies
                ) {
            if (cookie.getName().equals("current_user")) {
                String current_token = cookie.getValue();
                cookie.setValue("");
                cookie.setMaxAge(0);
                TokenServiceInterface tokenService = new TokenService();
                if (tokenService.findToken(current_token)!=null) {
                    tokenService.deleteToken(current_token);
                }
                resp.addCookie(cookie);
            }
        }
        req.getSession().invalidate();
        resp.sendRedirect("/posts");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}