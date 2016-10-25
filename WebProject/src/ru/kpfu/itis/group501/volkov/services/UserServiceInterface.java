package ru.kpfu.itis.group501.volkov.services;

import ru.kpfu.itis.group501.volkov.entities.Error;
import ru.kpfu.itis.group501.volkov.entities.User;

public interface UserServiceInterface {


    void add(String first_name, String last_name, String login, String password,String passwordAgain, String email, String country, String city, String about);
    void delete(String id);
    User find(String login) ;
    User findId(String id) ;
    Error getError();
}