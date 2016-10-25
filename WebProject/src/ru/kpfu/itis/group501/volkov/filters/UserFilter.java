package ru.kpfu.itis.group501.volkov.filters;

import ru.kpfu.itis.group501.volkov.helpers.CookieToSession;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by volkov on 24.10.2016.
 */
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        ((HttpServletRequest) req).getSession().setAttribute("current_user", CookieToSession.add(req));

        if (((HttpServletRequest) req).getSession().getAttribute("current_user") != null) {

            chain.doFilter(req, resp);
            return;
        } else {
            ((HttpServletResponse)resp).sendRedirect("/login");
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
