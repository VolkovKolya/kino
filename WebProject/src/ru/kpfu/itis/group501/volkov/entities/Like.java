package ru.kpfu.itis.group501.volkov.entities;

/**
 * Created by volkov on 25.10.2016.
 */
public class Like {
    private String id;
    private String user_id;
    private String post_id;


    public Like( String user_id, String post_id) {
        this(null,user_id,post_id);
    }
    public Like(String id, String user_id, String post_id) {
        this.id = id;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public String getId() {
        return id;
    }

}
