package ru.kpfu.itis.group501.volkov.entities;

/**
 * Created by volkov on 24.10.2016.
 */
public class Comment {
    private String id;
    private String text;
    private String user_id;
    private String data;
    private String post_id;
    private String userName;
    private String userImage;

    public Comment(String text, String user_id, String post_id, String data) {
        this(null,text,user_id,post_id,data);
    }
    public Comment(String id,String text, String user_id, String post_id, String data) {
        this.id = id;
        this.text = text;
        this.user_id = user_id;
        this.data = data;
        this.post_id = post_id;
    }

    public String getText() {
        return text;
    }
    public String getUser_id() {
        return user_id;
    }

    public String getData() {
        return data;
    }

    public String getPost_id() {
        return post_id;
    }
    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
    public String getUserImage() {return userImage;}

    public void setUserName(String s) {userName = s;}
    public void setUserImage(String s) { userImage = s;}
}
