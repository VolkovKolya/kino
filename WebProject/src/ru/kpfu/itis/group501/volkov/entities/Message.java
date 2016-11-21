package ru.kpfu.itis.group501.volkov.entities;

/**
 * Created by volkov on 18.11.2016.
 */
public class Message {
    private String id;
    private String text;
    private String user_id;
    private String data;
    private String from_user_id;
    private String userName;
    private String userImage;
    private User user;
    public Message(String text, String user_id, String from_user_id, String data) {
        this(null,text,user_id,from_user_id,data);
    }
    public Message(String id,String text, String user_id, String from_user_id, String data) {
        this.id = id;
        this.text = text;
        this.user_id = user_id;
        this.data = data;
        this.from_user_id= from_user_id;
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

    public String getFrom_user_id() {
        return from_user_id;
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


    public User getUser() {return user;}

    public void setUser(User s) {user = s;}
    public void setText(String s) {text = s;}
}
