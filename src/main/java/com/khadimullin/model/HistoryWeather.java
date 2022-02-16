package com.khadimullin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Table(name = "history")
public class HistoryWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String city;

    private String date;

    private Float temp;

    private Float feels_like;

    private String email;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public Float getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(Float feels_like) {
        this.feels_like = feels_like;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HistoryWeather(String city, String date, Float temp, Float feels_like, String email) {
        this.city = city;
        this.date = date;
        this.temp = temp;
        this.feels_like = feels_like;
        this.email = email;
    }
}
