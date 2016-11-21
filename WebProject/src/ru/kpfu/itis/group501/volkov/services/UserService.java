package ru.kpfu.itis.group501.volkov.services;


import ru.kpfu.itis.group501.volkov.dao.UserDao;
import ru.kpfu.itis.group501.volkov.entities.User;
import ru.kpfu.itis.group501.volkov.entities.Error;
import ru.kpfu.itis.group501.volkov.helpers.Hash;
import ru.kpfu.itis.group501.volkov.helpers.RegEx;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by volkov on 21.10.2016.
 */
public class UserService implements UserServiceInterface {
    private UserDao userDao = null;
    private Error error = null;

    public UserService() {
        this.userDao = new UserDao();
    }

    public void add(String first_name, String last_name, String login, String password,String passwordAgain, String email, String country, String city, String about,String image) {
        error = null;
        RegEx re = new RegEx();
        if (re.IsRight(first_name) && re.IsRight(last_name) && re.IsRight(login) && re.IsRight(password) && re.IsRight(passwordAgain)){
            if (password.equals(passwordAgain)) {
                password = Hash.getMd5Apache(password);
                User newUser = new User(first_name, last_name,login, password,email,country,city,about,image);
                if (userDao.findUser( login) == null) {
                    userDao.addUser(newUser);
                }
                else{
                    error = new Error("user_exist", "This login alredy exist!");
                }

            } else {
                error = new Error("wrong_password", "Wrong second password");
            }
        }else{
            error = new Error("wrong_words", "Wrong data!");
        }
    }


    public void update(String first_name, String last_name, String login, String password,String passwordAgain, String email, String country, String city, String about,String image,String id) {
        error = null;
        RegEx re = new RegEx();
        if (re.IsRight(first_name) && re.IsRight(last_name) && re.IsRight(login) && re.IsRight(password) && re.IsRight(passwordAgain)){
            if ((password !=null || passwordAgain !=null) &&  (!password.equals("") || !passwordAgain.equals(""))) {
                if (password.equals(passwordAgain)) {
                    password = Hash.getMd5Apache(password);
                    User newUser = new User(id,first_name, last_name, login, password, email, country, city, about, image);
                    userDao.updateUser(newUser);
                } else {
                    error = new Error("wrong_password", "Wrong second password");
                }
            }
            else {User newUser = new User(id,first_name, last_name, login, null, email, country, city, about, image);
                    userDao.updateUser(newUser);}
        }else{
            error = new Error("wrong_words", "Wrong data!");
        }
    }
    public void delete(String id) {
        error = null;
        if (userDao.findUserId(id)!=null)
        userDao.deleteUser(id);
        else {
            error = new Error("user_not_found", "User not found");
        }
    }

    public User find(String login) {
        error = null;
        User user = null;
        user = userDao.findUser(login);
        if (user == null) {
            error = new Error("user_not_found", "User not found");
            return null;
        } else {
            return user;
        }
    }

    public User findId(String id) {
        error = null;
        if (userDao.findUserId(id) == null) {
            error = new Error("user_not_found", "User not found");
            return null;
        } else {
            return userDao.findUserId(id);
        }
    }
    public Error getError(){
        return error;
    }


    public List getUser() {
        error = null;
        List<User> users = new ArrayList();
        users = userDao.getUsers();
        if (users == null) {
            error = new Error("users_not_found", "Users not found");
            return null;
        } else {
            return users;
        }
    }
}