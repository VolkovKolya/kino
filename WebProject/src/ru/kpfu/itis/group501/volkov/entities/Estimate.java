package ru.kpfu.itis.group501.volkov.entities;

/**
 * Created by volkov on 19.11.2016.
 */
public class Estimate {
    private String id;
    private String user_id;
    private String movie_id;
    private String estimate;


    public Estimate( String user_id, String movie_id,String estimate) {
        this(null,user_id,movie_id,estimate);
    }
    public Estimate(String id, String user_id, String movie_id,String estimate) {
        this.id = id;
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.estimate = estimate;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public String getId() {
        return id;
    }
    public String getEstimate(){return estimate;}
}
