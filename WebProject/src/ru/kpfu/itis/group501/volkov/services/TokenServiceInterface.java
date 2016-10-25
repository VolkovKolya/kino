package ru.kpfu.itis.group501.volkov.services;

/**
 * Created by volkov on 23.10.2016.
 */
public interface TokenServiceInterface {
    void addToken(String id, String token);

    void updateToken(String id, String token);

    void deleteToken(String token);
    String findToken(String token) ;
}
