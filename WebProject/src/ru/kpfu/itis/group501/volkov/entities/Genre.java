package ru.kpfu.itis.group501.volkov.entities;

/**
 * Created by volkov on 18.11.2016.
 */
public class Genre {
    private  String name;
    private  String is;

    public Genre(String name, String is) {
        this.name = name;
        this.is = is;
    }

    public  String getName() {
        return name;
    }

    public  String getIs() {
        return is;
    }
}
