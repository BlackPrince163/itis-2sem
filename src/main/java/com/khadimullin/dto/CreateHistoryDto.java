package com.khadimullin.dto;

import javax.validation.constraints.NotBlank;

public class CreateHistoryDto {

    @NotBlank(message = "City shouldn't be blank!")
    private String city;

    @NotBlank(message = "Email shouldn't be blank!")
    private String email;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CreateHistoryDto(String city, String email) {
        this.city = city;
        this.email = email;
    }
}
