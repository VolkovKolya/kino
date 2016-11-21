package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.entities.User;

import java.util.List;

/**
 * Created by volkov on 23.10.2016.
 */
public interface UserDaoInterface {
    void addUser(User user) ;

    User findUser(String login) ;
    User findUserId(String id) ;


    void deleteUser(String id) ;

}
