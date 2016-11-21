package ru.kpfu.itis.group501.volkov.entities;

/**
 * Created by volkov on 28.10.2016.
 */
public class Film {
    private String id;
    private String name;
    private String description;
    private String video;
    private String image;
    private String year;
    private String country;
    private String producer;
    private String actors;
    private String estimate;
    private String genre;
    private String number;

    public Film(String name,String description, String video, String image,String year,String country, String producer, String actors) {
        this(null,name,description,video,image,year,country,producer,actors);
    }
    public Film(String id,String name,String description, String video, String image,String year,String country, String producer, String actors) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.video = video;
        this.image = image;
        this.year = year;
        this.country = country;
        this.producer = producer;
        this.actors = actors;
    }
    public String getDescription() {
        return description;
    }

    public String getVideo() {
        return video;
    }
    public String getImage() {
        return image;
    }

    public String getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

    public String getActors() {
        return actors;
    }

    public String getEstimate(){return estimate;}
    public String getGenre(){return genre;}
    public void setEstimate(String s){estimate = s;}
    public void setGenre(String s){genre = s;}


    public String getNumber(){return number;}
    public void setNumber(String s){number = s;}
}
