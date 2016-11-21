package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.entities.Estimate;

/**
 * Created by volkov on 19.11.2016.
 */
public interface EstimateDaoInterface {
    void add(Estimate estimate) ;
    void delete(String user_id,String movie_id) ;

}
