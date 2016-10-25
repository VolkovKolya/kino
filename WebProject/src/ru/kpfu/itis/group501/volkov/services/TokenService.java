package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.dao.TokenDao;

/**
 * Created by volkov on 21.10.2016.
 */
public class TokenService implements TokenServiceInterface {
    TokenDao tokenDao = null;

    public TokenService() {
        tokenDao = new TokenDao();
    }

    public void addToken(String id, String token) {
        tokenDao.addToken(id,token);
    }

    public void updateToken(String id, String token) {
        tokenDao.updateToken(id,token);
    }

    public void deleteToken(String token) {
        tokenDao.deleteToken(token);
    }
    //return id user
    public String findToken(String token) {
        return tokenDao.findToken(token);
    }
}