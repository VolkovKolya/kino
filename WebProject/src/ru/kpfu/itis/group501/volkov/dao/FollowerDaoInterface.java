package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.entities.User;

import java.util.List;

/**
 * Created by volkov on 18.11.2016.
 */
public interface FollowerDaoInterface {
    void addFollower(String user_id,String profile_id) ;
    void deleteFollower(String user_id, String profile_id) ;
    List<User> getFollowers(String id);
}
