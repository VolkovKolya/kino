package ru.kpfu.itis.group501.volkov.entities;

/**
 * Created by volkov on 23.10.2016.
 */
public class Post {
    private String id;
    private String title;
    private String text;
    private String user_id;
    private String data;
    private String video;
    private String image;

    public Post(String title,String text, String user_id, String data,String video,String image) {
        this(null,title,text,user_id,data,video,image);
    }
    public Post(String id,String title,String text, String user_id, String data,String video,String image) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.user_id = user_id;
        this.data = data;
        this.video = video;
        this.video = image;
    }
    public String getTitle() {
        return title;
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

    public String getVideo() {
        return video;
    }
    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}