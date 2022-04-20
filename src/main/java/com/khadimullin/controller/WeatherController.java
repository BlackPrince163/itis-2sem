package com.khadimullin.controller;

import com.khadimullin.model.Appeal;
import com.khadimullin.model.HistoryWeather;
import com.khadimullin.model.User;
import com.khadimullin.repository.AppealRepository;
import com.khadimullin.repository.HistoryRepository;
import com.khadimullin.repository.UserRepository;
import com.khadimullin.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WeatherController {
    private final Service weatherService = new Service();
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final AppealRepository appealRepository;


    @Autowired
    public WeatherController(HistoryRepository historyRepository, UserRepository userRepository, AppealRepository appealRepository) {
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
        this.appealRepository = appealRepository;
    }

    @Operation(summary = "Add weather")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Weather added",
                    content = {
                            @Content(mediaType = "application/json")
                    }
            )
    })
    @GetMapping("/weather")
    public HistoryWeather addWeather(@Valid @RequestParam Optional<String> city, @RequestParam int user_id) throws IOException {
        Map<String, String> result = weatherService.get(city.orElse("Kazan"));
        HistoryWeather weather = new HistoryWeather(result.get("name"), result.get("temp"), result.get("feels_like"), result.get("wind_speed"), new Date().getTime());
        appealRepository.save(new Appeal(userRepository.getById(user_id), weather, result.get("name")));
        return historyRepository.save(weather);
    }

    @GetMapping("/getAllRequestById")
    public String getAllRequestByID(@RequestParam int id) {
        List<Appeal> requestList = appealRepository.findAll();
        String result = "";
        for (Appeal request : requestList) {
            if (request.getUser().getId() == id) {
                result += request.toString() + "\n";
            }
        }
        return result;
    }

    @GetMapping("/getAllRequestByCity")
    public String getAllRequestByCity(@RequestParam String city) {
        List<Appeal> requestList = appealRepository.findAll();
        String result = "";
        for (Appeal appeal : requestList) {
            if (appeal.getCity().equals(city)) {
                result += appeal.toString() + "\n";
            }
        }
        return result;
    }

    @GetMapping("/getAllWeatherByCity")
    public String getAllWeatherByCity(@RequestParam String city) {
        List<HistoryWeather> weatherList = historyRepository.findAll();
        String result = "";
        for (HistoryWeather weather : weatherList) {
            if (weather.getCity().equals(city)) {
                result += weather.toString() + "\n";
            }
        }
        return result;
    }

    @GetMapping("/getWeather")
    public String getWeather(@RequestParam String email) {
        List<User> users = userRepository.findAll();
        List<HistoryWeather> weatherList = historyRepository.findAll();
        String result = "";
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                for (HistoryWeather weather : weatherList) {
                    result += weather.toString() + "\n";
                }
            }
        }
        return result;
    }

    @GetMapping("/getAll")
    public String getAll(){
        List<User> users = userRepository.findAll();
        List<HistoryWeather> weatherList = historyRepository.findAll();
        String result = "";
        for (User user : users) {
            result += user.toString() + "\n";
        }
        for (HistoryWeather weather : weatherList) {
            result += weather.toString()+"\n";
        }
        return result;
    }
}
