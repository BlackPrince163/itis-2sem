package com.khadimullin.model;

import javax.persistence.*;

@Entity
public class Appeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "weather_id")
    private HistoryWeather HistoryWeather;

    public Appeal() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public com.khadimullin.model.HistoryWeather getHistoryWeather() {
        return HistoryWeather;
    }

    public void setHistoryWeather(com.khadimullin.model.HistoryWeather historyWeather) {
        HistoryWeather = historyWeather;
    }
}
