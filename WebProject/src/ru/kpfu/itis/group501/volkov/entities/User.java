package ru.kpfu.itis.group501.volkov.entities;

import java.util.List;

/**
 * Created by volkov on 18.10.2016.
 */
public class User {
    private String id;
    private String first_name;
	private String last_name;
    private String login;
    private String password;
	private String email;
    private String country;
	private String city;
    private String about;
    private boolean is_admin;


    public User(String first_name,String last_name, String login, String password,String email,String country, String city, String about) {
		this(null,first_name,last_name,login,password,email,country,city,about);
    }
    public User(String id,String first_name,String last_name, String login, String password,String email,String country, String city, String about) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.country = country;
        this.city = city;
        this.about = about;
        this.is_admin = false;
    }
	public String getCity() {
        return city;
    }
	
	public String getAbout() {
        return about;
    }
	public String getEmail() {
        return email;
    }
	
	public String getCountry() {
        return country;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getFirst_Name() {
        return first_name;
    }
	
	public String getLast_Name() {
        return last_name;
    }

    public boolean getIs_admin() {
        return is_admin;
    }

    public String getLogin() {
        return login;
    }


    @Override
    public String toString() {
        return getFirst_Name();
    }
}