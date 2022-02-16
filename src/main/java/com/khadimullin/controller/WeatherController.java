package com.khadimullin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.khadimullin.dto.CreateHistoryDto;
import com.khadimullin.dto.HistoryDto;
import com.khadimullin.helper.HistoryHelper;
import com.khadimullin.helper.Json;
import com.khadimullin.model.HistoryWeather;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WeatherController {
    private Json json;

    @PostMapping("/history")
    public HistoryDto createWeather(@Valid @RequestBody CreateHistoryDto form) {
        Map<String, String> jsonMap;
        try {
            jsonMap = json.parseJson(HistoryHelper.getWeather(form.getCity()));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        HistoryWeather history = new HistoryWeather(form.getEmail(), jsonMap.get("city"),
                jsonMap.get("temp"),
                jsonMap.get("felas_like"));


        return HistoryDto();
    }

    @GetMapping("/history")
    public List<HistoryDto> getWeather() {
        return HistoryDto.fromModel();
    }
}
