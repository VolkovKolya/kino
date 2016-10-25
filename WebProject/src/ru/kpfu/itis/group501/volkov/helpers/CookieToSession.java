package ru.kpfu.itis.group501.volkov.helpers;

import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.services.TokenService;
import ru.kpfu.itis.group501.volkov.services.TokenServiceInterface;
import ru.kpfu.itis.group501.volkov.services.UserService;
import ru.kpfu.itis.group501.volkov.services.UserServiceInterface;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by volkov on 24.10.2016.
 */
public class CookieToSession {
        public static User add(ServletRequest req){
            Cookie[] cookies = ((HttpServletRequest) req).getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("current_user")) {
                        TokenServiceInterface tokenService = new TokenService();
                        String id = tokenService.findToken(cookie.getValue());
                        UserServiceInterface userService = new UserService();
                        return userService.findId(id);


                    }
                }
            }
            return null;
        }

}
